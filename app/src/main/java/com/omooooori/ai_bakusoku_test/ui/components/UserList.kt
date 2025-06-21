package com.omooooori.ai_bakusoku_test.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.omooooori.ai_bakusoku_test.User
import com.omooooori.ai_bakusoku_test.ui.theme.AibakusokutestTheme

@Composable
fun UserList(
    users: List<User>,
    onDeleteUser: (User) -> Unit,
    onUserClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "ユーザー一覧 (${users.size})",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        if (users.isEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Group,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "ユーザーが登録されていません",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "ユーザーを追加してください",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(users) { user ->
                    UserCard(
                        user = user,
                        onDelete = { onDeleteUser(user) },
                        onClick = { onUserClick(user.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun UserCard(
    user: User,
    onDelete: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = user.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    if (user.position.isNotEmpty()) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Surface(
                            shape = MaterialTheme.shapes.small,
                            color = MaterialTheme.colorScheme.primaryContainer
                        ) {
                            Text(
                                text = user.position,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
                
                Text(
                    text = user.email,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "年齢: ${user.age}歳",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    
                    if (user.department.isNotEmpty()) {
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "部署: ${user.department}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                
                if (user.skills.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "スキル: ${user.skills.take(3).joinToString(", ")}${if (user.skills.size > 3) "..." else ""}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    onClick = onDelete,
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "削除",
                        tint = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
                
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "詳細表示",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(16.dp)
                )
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
                User(
                    id = "1",
                    name = "田中太郎",
                    email = "tanaka@example.com",
                    age = 30,
                    department = "開発部",
                    position = "シニアエンジニア",
                    skills = listOf("Kotlin", "Android", "Jetpack Compose")
                ),
                User(
                    id = "2",
                    name = "佐藤花子",
                    email = "sato@example.com",
                    age = 25,
                    department = "デザイン部",
                    position = "UIデザイナー",
                    skills = listOf("Figma", "Adobe XD", "Photoshop")
                )
            ),
            onDeleteUser = {},
            onUserClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserListEmptyPreview() {
    AibakusokutestTheme {
        UserList(
            users = emptyList(),
            onDeleteUser = {},
            onUserClick = {}
        )
    }
} 