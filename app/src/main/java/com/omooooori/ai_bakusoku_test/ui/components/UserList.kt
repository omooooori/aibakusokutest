package com.omooooori.ai_bakusoku_test.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.omooooori.ai_bakusoku_test.User
import com.omooooori.ai_bakusoku_test.ui.theme.AibakusokutestTheme

@Composable
fun UserList(
    users: List<User>,
    onDeleteUser: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Users (${users.size})",
            style = MaterialTheme.typography.headlineSmall
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        if (users.isEmpty()) {
            Text(
                text = "No users added yet",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            LazyColumn(
                modifier = Modifier.height(200.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(users) { user ->
                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = user.name,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Text(
                                    text = user.email,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = "Age: ${user.age}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            IconButton(onClick = { onDeleteUser(user.id) }) {
                                Text("×", style = MaterialTheme.typography.headlineSmall)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserListPreview() {
    AibakusokutestTheme {
        UserList(
            users = listOf(
                User("1", "John Doe", "john@example.com", 30),
                User("2", "Jane Smith", "jane@example.com", 25)
            ),
            onDeleteUser = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserListEmptyPreview() {
    AibakusokutestTheme {
        UserList(
            users = emptyList(),
            onDeleteUser = {}
        )
    }
} 