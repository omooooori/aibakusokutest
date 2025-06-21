package com.omooooori.ai_bakusoku_test.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.omooooori.ai_bakusoku_test.ui.theme.AibakusokutestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    var isEditing by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("田中太郎") }
    var email by remember { mutableStateOf("tanaka@example.com") }
    var bio by remember { mutableStateOf("Android開発者として活動中。Jetpack ComposeとKotlinが大好きです。") }
    
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // プロフィールヘッダー
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // アバター
                    Surface(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape),
                        color = MaterialTheme.colorScheme.primaryContainer
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                modifier = Modifier.size(50.dp),
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // 名前
                    Text(
                        text = name,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    
                    // メール
                    Text(
                        text = email,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // 編集ボタン
                    Button(
                        onClick = { isEditing = !isEditing },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = if (isEditing) Icons.Default.Save else Icons.Default.Edit,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(if (isEditing) "保存" else "編集")
                    }
                }
            }
        }
        
        // プロフィール情報
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "プロフィール情報",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    if (isEditing) {
                        // 編集モード
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("名前") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text("メールアドレス") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        OutlinedTextField(
                            value = bio,
                            onValueChange = { bio = it },
                            label = { Text("自己紹介") },
                            modifier = Modifier.fillMaxWidth(),
                            minLines = 3
                        )
                    } else {
                        // 表示モード
                        ProfileInfoRow("名前", name, Icons.Default.Person)
                        ProfileInfoRow("メール", email, Icons.Default.Email)
                        ProfileInfoRow("自己紹介", bio, Icons.Default.Info)
                    }
                }
            }
        }
        
        // 統計情報
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "統計情報",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        StatItem("投稿", "42", Icons.Default.Article)
                        StatItem("フォロワー", "1,234", Icons.Default.Group)
                        StatItem("フォロー", "567", Icons.Default.Person)
                    }
                }
            }
        }
        
        // 最近の活動
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "最近の活動",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    val activities = listOf(
                        "新しいプロジェクトを作成しました",
                        "コードレビューを完了しました",
                        "チームミーティングに参加しました",
                        "バグ修正を完了しました",
                        "ドキュメントを更新しました"
                    )
                    
                    activities.forEachIndexed { index, activity ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.History,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = activity,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        
                        if (index < activities.size - 1) {
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }
                }
            }
        }
        
        // 設定メニュー
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "設定",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    val settings = listOf(
                        Triple("通知設定", Icons.Default.Notifications, Color.Blue),
                        Triple("プライバシー", Icons.Default.Security, Color.Green),
                        Triple("アカウント", Icons.Default.AccountCircle, Color(0xFFFF9800)),
                        Triple("ヘルプ", Icons.Default.Help, Color.Gray)
                    )
                    
                    settings.forEach { (title, icon, tint) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = tint,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = title,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.weight(1f)
                            )
                            Icon(
                                imageVector = Icons.Default.ChevronRight,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileInfoRow(
    label: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun StatItem(
    label: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    AibakusokutestTheme {
        ProfileScreen()
    }
} 