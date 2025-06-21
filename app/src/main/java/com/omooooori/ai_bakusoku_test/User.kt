package com.omooooori.ai_bakusoku_test

data class User(
    val id: String,
    val name: String,
    val email: String,
    val age: Int
)

data class CalculationResult(
    val firstNumber: Double,
    val secondNumber: Double,
    val operation: String,
    val result: Double,
    val isError: Boolean = false,
    val errorMessage: String? = null
)

class UserRepository {
    private val users = mutableListOf<User>()
    
    fun addUser(user: User): Boolean {
        return if (isValidUser(user)) {
            users.add(user)
            true
        } else {
            false
        }
    }
    
    fun getUserById(id: String): User? {
        return users.find { it.id == id }
    }
    
    fun getAllUsers(): List<User> {
        return users.toList()
    }
    
    fun updateUser(user: User): Boolean {
        val index = users.indexOfFirst { it.id == user.id }
        return if (index != -1 && isValidUser(user)) {
            users[index] = user
            true
        } else {
            false
        }
    }
    
    fun deleteUser(id: String): Boolean {
        val index = users.indexOfFirst { it.id == id }
        return if (index != -1) {
            users.removeAt(index)
            true
        } else {
            false
        }
    }
    
    private fun isValidUser(user: User): Boolean {
        return user.name.length >= 2 &&
               user.email.contains("@") &&
               user.email.contains(".") &&
               user.age > 0 &&
               user.age < 150
    }
}

class CalculatorService {
    fun calculate(num1: Double, num2: Double, operation: String): CalculationResult {
        return try {
            val result = when (operation) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                "/" -> {
                    if (num2 == 0.0) {
                        return CalculationResult(
                            firstNumber = num1,
                            secondNumber = num2,
                            operation = operation,
                            result = 0.0,
                            isError = true,
                            errorMessage = "Division by zero"
                        )
                    }
                    num1 / num2
                }
                else -> {
                    return CalculationResult(
                        firstNumber = num1,
                        secondNumber = num2,
                        operation = operation,
                        result = 0.0,
                        isError = true,
                        errorMessage = "Invalid operation"
                    )
                }
            }
            CalculationResult(
                firstNumber = num1,
                secondNumber = num2,
                operation = operation,
                result = result
            )
        } catch (e: Exception) {
            CalculationResult(
                firstNumber = num1,
                secondNumber = num2,
                operation = operation,
                result = 0.0,
                isError = true,
                errorMessage = e.message
            )
        }
    }
} 