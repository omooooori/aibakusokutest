package com.omooooori.ai_bakusoku_test.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.omooooori.ai_bakusoku_test.MainViewModel
import com.omooooori.ai_bakusoku_test.ui.components.*
import com.omooooori.ai_bakusoku_test.ui.theme.AibakusokutestTheme

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    LaunchedEffect(uiState.errorMessage, uiState.successMessage) {
        if (uiState.errorMessage != null || uiState.successMessage != null) {
            kotlinx.coroutines.delay(3000)
            viewModel.clearMessages()
        }
    }
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Greeting(name = "Android")
        
        if (uiState.isLoading) {
            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator()
        }
        
        if (uiState.errorMessage != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = uiState.errorMessage!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        
        if (uiState.successMessage != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = uiState.successMessage!!,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        Calculator(viewModel = viewModel)
        Spacer(modifier = Modifier.height(32.dp))
        UserInputForm(viewModel = viewModel)
        Spacer(modifier = Modifier.height(32.dp))
        UserList(users = uiState.users, onDeleteUser = { viewModel.deleteUser(it) })
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    AibakusokutestTheme {
        // Preview without ViewModel for now
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Greeting("Android")
        }
    }
} 