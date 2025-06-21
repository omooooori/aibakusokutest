package com.omooooori.ai_bakusoku_test.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.omooooori.ai_bakusoku_test.MainViewModel
import com.omooooori.ai_bakusoku_test.ui.theme.AibakusokutestTheme

@Composable
fun Calculator(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var operation by remember { mutableStateOf("") }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calculator",
            style = MaterialTheme.typography.headlineSmall
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = number1,
            onValueChange = { number1 = it },
            label = { Text("First Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = number2,
            onValueChange = { number2 = it },
            label = { Text("Second Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { operation = "+" }) {
                Text("+")
            }
            Button(onClick = { operation = "-" }) {
                Text("-")
            }
            Button(onClick = { operation = "*" }) {
                Text("×")
            }
            Button(onClick = { operation = "/" }) {
                Text("÷")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = {
                viewModel.performCalculation(number1, number2, operation)
            },
            enabled = number1.isNotEmpty() && number2.isNotEmpty() && operation.isNotEmpty()
        ) {
            Text("Calculate")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    AibakusokutestTheme {
        // Preview without ViewModel for now
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Calculator",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
} 