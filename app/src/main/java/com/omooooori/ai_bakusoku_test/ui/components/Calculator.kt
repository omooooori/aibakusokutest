package com.omooooori.ai_bakusoku_test.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.omooooori.ai_bakusoku_test.ui.theme.AibakusokutestTheme

@Composable
fun Calculator(
    onCalculate: (Double, Double, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var operation by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<Double?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "計算機",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = number1,
            onValueChange = { 
                number1 = it
                errorMessage = null
            },
            label = { Text("1つ目の数値") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = number2,
            onValueChange = { 
                number2 = it
                errorMessage = null
            },
            label = { Text("2つ目の数値") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { operation = "+" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (operation == "+") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("+")
            }
            Button(
                onClick = { operation = "-" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (operation == "-") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("-")
            }
            Button(
                onClick = { operation = "*" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (operation == "*") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("×")
            }
            Button(
                onClick = { operation = "/" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (operation == "/") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("÷")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = {
                try {
                    val num1 = number1.toDouble()
                    val num2 = number2.toDouble()
                    onCalculate(num1, num2, operation)
                    
                    // 簡単な計算結果を表示
                    result = when (operation) {
                        "+" -> num1 + num2
                        "-" -> num1 - num2
                        "*" -> num1 * num2
                        "/" -> {
                            if (num2 == 0.0) {
                                errorMessage = "0で割ることはできません"
                                null
                            } else {
                                num1 / num2
                            }
                        }
                        else -> null
                    }
                } catch (e: NumberFormatException) {
                    errorMessage = "有効な数値を入力してください"
                }
            },
            enabled = number1.isNotEmpty() && number2.isNotEmpty() && operation.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("計算")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // 結果表示
        if (result != null) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "計算結果",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "$number1 $operation $number2 = ${String.format("%.2f", result)}",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
        
        // エラーメッセージ表示
        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Text(
                    text = errorMessage!!,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    AibakusokutestTheme {
        Calculator(
            onCalculate = { _, _, _ -> }
        )
    }
} 