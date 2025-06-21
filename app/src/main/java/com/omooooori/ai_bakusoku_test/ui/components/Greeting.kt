package com.omooooori.ai_bakusoku_test.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.omooooori.ai_bakusoku_test.ui.theme.AibakusokutestTheme

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        style = MaterialTheme.typography.headlineMedium
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AibakusokutestTheme {
        Greeting("Android")
    }
} 