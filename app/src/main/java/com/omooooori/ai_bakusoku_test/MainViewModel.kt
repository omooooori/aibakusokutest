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
    }
    
    fun addUser(name: String, email: String, age: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            
            try {
                val ageInt = age.toIntOrNull()
                if (ageInt == null) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Invalid age format"
                    )
                    return@launch
                }
                
                val user = User(
                    id = UUID.randomUUID().toString(),
                    name = name,
                    email = email,
                    age = ageInt
                )
                
                val success = userRepository.addUser(user)
                if (success) {
                    loadUsers()
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        successMessage = "User added successfully"
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Invalid user data"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Unknown error occurred"
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
                        successMessage = "User updated successfully"
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Failed to update user"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Unknown error occurred"
                )
            }
        }
    }
    
    fun deleteUser(userId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            
            try {
                val success = userRepository.deleteUser(userId)
                if (success) {
                    loadUsers()
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        successMessage = "User deleted successfully"
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Failed to delete user"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Unknown error occurred"
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
                        errorMessage = "Invalid number format"
                    )
                    return@launch
                }
                
                val result = calculatorService.calculate(firstNumber, secondNumber, operation)
                val currentHistory = _uiState.value.calculationHistory.toMutableList()
                currentHistory.add(result)
                
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    calculationHistory = currentHistory,
                    successMessage = if (!result.isError) "Calculation completed" else null,
                    errorMessage = result.errorMessage
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Unknown error occurred"
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
                    errorMessage = if (user == null) "User not found" else null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Unknown error occurred"
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
                    errorMessage = e.message ?: "Failed to load users"
                )
            }
        }
    }
} 