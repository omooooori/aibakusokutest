package com.omooooori.ai_bakusoku_test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

data class MainUiState(
    val users: List<User> = emptyList(),
    val currentUser: User? = null,
    val calculationHistory: List<CalculationResult> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null
)

class MainViewModel : ViewModel() {
    private val userRepository = UserRepository()
    private val calculatorService = CalculatorService()
    
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()
    
    init {
        loadUsers()
        // サンプルユーザーを追加
        addSampleUsers()
    }
    
    fun addUser(name: String, email: String, age: String, department: String = "", position: String = "", skills: String = "") {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            
            try {
                val ageInt = age.toIntOrNull()
                if (ageInt == null) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "年齢の形式が無効です"
                    )
                    return@launch
                }
                
                val skillsList = if (skills.isNotEmpty()) {
                    skills.split(",").map { it.trim() }
                } else {
                    emptyList()
                }
                
                val user = User(
                    id = UUID.randomUUID().toString(),
                    name = name,
                    email = email,
                    age = ageInt,
                    department = department,
                    position = position,
                    skills = skillsList
                )
                
                val success = userRepository.addUser(user)
                if (success) {
                    loadUsers()
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        successMessage = "ユーザーが正常に追加されました"
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "無効なユーザーデータです"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "不明なエラーが発生しました"
                )
            }
        }
    }
    
    fun updateUser(user: User) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            
            try {
                val success = userRepository.updateUser(user)
                if (success) {
                    loadUsers()
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        successMessage = "ユーザーが正常に更新されました"
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "ユーザーの更新に失敗しました"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "不明なエラーが発生しました"
                )
            }
        }
    }
    
    fun deleteUser(user: User) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            
            try {
                val success = userRepository.deleteUser(user.id)
                if (success) {
                    loadUsers()
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        successMessage = "ユーザーが正常に削除されました"
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "ユーザーの削除に失敗しました"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "不明なエラーが発生しました"
                )
            }
        }
    }
    
    fun performCalculation(num1: String, num2: String, operation: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            
            try {
                val firstNumber = num1.toDoubleOrNull()
                val secondNumber = num2.toDoubleOrNull()
                
                if (firstNumber == null || secondNumber == null) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "数値の形式が無効です"
                    )
                    return@launch
                }
                
                val result = calculatorService.calculate(firstNumber, secondNumber, operation)
                val currentHistory = _uiState.value.calculationHistory.toMutableList()
                currentHistory.add(result)
                
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    calculationHistory = currentHistory,
                    successMessage = if (!result.isError) "計算が完了しました" else null,
                    errorMessage = result.errorMessage
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "不明なエラーが発生しました"
                )
            }
        }
    }
    
    fun getUserById(userId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            
            try {
                val user = userRepository.getUserById(userId)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    currentUser = user,
                    errorMessage = if (user == null) "ユーザーが見つかりません" else null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "不明なエラーが発生しました"
                )
            }
        }
    }
    
    fun clearMessages() {
        _uiState.value = _uiState.value.copy(
            errorMessage = null,
            successMessage = null
        )
    }
    
    private fun loadUsers() {
        viewModelScope.launch {
            try {
                val users = userRepository.getAllUsers()
                _uiState.value = _uiState.value.copy(users = users)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = e.message ?: "ユーザーの読み込みに失敗しました"
                )
            }
        }
    }
    
    private fun addSampleUsers() {
        val sampleUsers = listOf(
            User(
                id = "1",
                name = "田中太郎",
                email = "tanaka@example.com",
                age = 30,
                department = "開発部",
                position = "シニアエンジニア",
                skills = listOf("Kotlin", "Android", "Jetpack Compose", "MVVM")
            ),
            User(
                id = "2",
                name = "佐藤花子",
                email = "sato@example.com",
                age = 25,
                department = "デザイン部",
                position = "UIデザイナー",
                skills = listOf("Figma", "Adobe XD", "Photoshop", "Illustrator")
            ),
            User(
                id = "3",
                name = "鈴木一郎",
                email = "suzuki@example.com",
                age = 35,
                department = "マネジメント部",
                position = "プロジェクトマネージャー",
                skills = listOf("プロジェクト管理", "アジャイル", "スクラム")
            )
        )
        
        sampleUsers.forEach { user ->
            userRepository.addUser(user)
        }
        loadUsers()
    }
} 