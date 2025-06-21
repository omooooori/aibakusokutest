package com.omooooori.ai_bakusoku_test.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.omooooori.ai_bakusoku_test.User
import com.omooooori.ai_bakusoku_test.ui.theme.AibakusokutestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    userId: String,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    // サンプルユーザーデータ（実際のアプリではViewModelから取得）
    val user = remember {
        User(
            id = userId,
            name = "田中太郎",
            email = "tanaka@example.com",
            age = 28,
            department = "開発部",
            position = "シニアエンジニア",
            joinDate = "2020年4月",
            skills = listOf("Kotlin", "Android", "Jetpack Compose", "MVVM"),
            projects = listOf(
                "AI Bakusoku Test App",
                "E-commerce Platform",
                "Social Media App"
            )
        )
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ユーザー詳細") },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "戻る"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* 編集 */ }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "編集"
                        )
                    }
                    IconButton(onClick = { /* 削除 */ }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "削除"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // ユーザー基本情報
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
                                .size(120.dp)
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
                                    modifier = Modifier.size(60.dp),
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // 名前
                        Text(
                            text = user.name,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                        
                        // 役職
                        Text(
                            text = user.position,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        
                        // 部署
                        Text(
                            text = user.department,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // アクションボタン
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(
                                onClick = { /* メッセージ送信 */ },
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Email,
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("メッセージ")
                            }
                            
                            Spacer(modifier = Modifier.width(8.dp))
                            
                            OutlinedButton(
                                onClick = { /* 通話 */ },
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Call,
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("通話")
                            }
                        }
                    }
                }
            }
            
            // 連絡先情報
            item {
                DetailSection(
                    title = "連絡先情報",
                    icon = Icons.Default.Person,
                    iconTint = Color.Blue
                ) {
                    DetailInfoRow("メールアドレス", user.email, Icons.Default.Email)
                    DetailInfoRow("電話番号", "090-1234-5678", Icons.Default.Phone)
                    DetailInfoRow("住所", "東京都渋谷区...", Icons.Default.LocationOn)
                }
            }
            
            // 基本情報
            item {
                DetailSection(
                    title = "基本情報",
                    icon = Icons.Default.Info,
                    iconTint = Color.Green
                ) {
                    DetailInfoRow("年齢", "${user.age}歳", Icons.Default.Person)
                    DetailInfoRow("入社日", user.joinDate, Icons.Default.DateRange)
                    DetailInfoRow("社員番号", "EMP-${user.id}", Icons.Default.AccountBox)
                }
            }
            
            // スキル
            item {
                DetailSection(
                    title = "スキル",
                    icon = Icons.Default.Star,
                    iconTint = Color(0xFFFF9800) // Orange
                ) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(user.skills) { skill ->
                            SkillChip(skill = skill)
                        }
                    }
                }
            }
            
            // プロジェクト履歴
            item {
                DetailSection(
                    title = "プロジェクト履歴",
                    icon = Icons.Default.Work,
                    iconTint = Color(0xFF9C27B0) // Purple
                ) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(user.projects) { project ->
                            ProjectCard(project = project)
                        }
                    }
                }
            }
            
            // 最近の活動
            item {
                DetailSection(
                    title = "最近の活動",
                    icon = Icons.Default.History,
                    iconTint = Color.Gray
                ) {
                    val activities = listOf(
                        "プロジェクト会議に参加",
                        "コードレビューを完了",
                        "新しい機能を実装",
                        "バグ修正を完了",
                        "ドキュメントを更新"
                    )
                    
                    activities.forEachIndexed { index, activity ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
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
            
            // 統計情報
            item {
                DetailSection(
                    title = "統計情報",
                    icon = Icons.Default.BarChart,
                    iconTint = Color.Red
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        DetailStatCard(
                            title = "完了プロジェクト",
                            value = "12",
                            icon = Icons.Default.CheckCircle,
                            modifier = Modifier.weight(1f)
                        )
                        DetailStatCard(
                            title = "進行中",
                            value = "3",
                            icon = Icons.Default.Schedule,
                            modifier = Modifier.weight(1f)
                        )
                        DetailStatCard(
                            title = "評価",
                            value = "4.8",
                            icon = Icons.Default.Star,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DetailSection(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            content()
        }
    }
}

@Composable
fun DetailInfoRow(
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
fun SkillChip(skill: String) {
    Surface(
        modifier = Modifier.wrapContentWidth(),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Text(
            text = skill,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Composable
fun ProjectCard(project: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Folder,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = project,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun DetailStatCard(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    AibakusokutestTheme {
        DetailScreen(
            userId = "1",
            onNavigateBack = {}
        )
    }
} 