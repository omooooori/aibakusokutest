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
fun UserInputForm(
    onAddUser: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var department by remember { mutableStateOf("") }
    var position by remember { mutableStateOf("") }
    var skills by remember { mutableStateOf("") }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "ユーザー追加",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("名前 *") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("メールアドレス *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("年齢 *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = department,
            onValueChange = { department = it },
            label = { Text("部署") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = position,
            onValueChange = { position = it },
            label = { Text("役職") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = skills,
            onValueChange = { skills = it },
            label = { Text("スキル（カンマ区切り）") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = {
                if (name.isNotEmpty() && email.isNotEmpty() && age.isNotEmpty()) {
                    onAddUser(name, email)
                    // フォームをクリア
                    name = ""
                    email = ""
                    age = ""
                    department = ""
                    position = ""
                    skills = ""
                }
            },
            enabled = name.isNotEmpty() && email.isNotEmpty() && age.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ユーザーを追加")
        }
        
        if (name.isEmpty() || email.isEmpty() || age.isEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "* は必須項目です",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserInputFormPreview() {
    AibakusokutestTheme {
        UserInputForm(
            onAddUser = { _, _ -> }
        )
    }
} 