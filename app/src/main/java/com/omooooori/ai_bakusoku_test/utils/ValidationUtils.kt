package com.omooooori.ai_bakusoku_test.utils

/**
 * フォームのバリデーションを行うユーティリティ関数
 */
fun validateForm(name: String, email: String, age: String): Boolean {
    val isNameValid = name.length >= 2
    val isEmailValid = email.contains("@") && email.contains(".")
    val isAgeValid = try {
        val ageNum = age.toInt()
        ageNum > 0 && ageNum < 150
    } catch (e: NumberFormatException) {
        false
    }
    return isNameValid && isEmailValid && isAgeValid
}

/**
 * 計算結果を文字列として返すユーティリティ関数（レガシー互換性のため）
 */
fun calculateResult(num1: String, num2: String, operation: String): String {
    return try {
        val a = num1.toDouble()
        val b = num2.toDouble()
        val result = when (operation) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> if (b != 0.0) a / b else "Error: Division by zero"
            else -> "Error: Invalid operation"
        }
        result.toString()
    } catch (e: NumberFormatException) {
        "Error: Invalid numbers"
    }
} 