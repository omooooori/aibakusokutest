package com.omooooori.ai_bakusoku_test.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.omooooori.ai_bakusoku_test.ui.theme.AibakusokutestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier
) {
    var notificationsEnabled by remember { mutableStateOf(true) }
    var darkModeEnabled by remember { mutableStateOf(false) }
    var autoSaveEnabled by remember { mutableStateOf(true) }
    var language by remember { mutableStateOf("日本語") }
    var fontSize by remember { mutableStateOf("中") }
    
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // ヘッダー
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "設定",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "アプリの設定をカスタマイズできます",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
        
        // 通知設定
        item {
            SettingsSection(
                title = "通知設定",
                icon = Icons.Default.Notifications,
                iconTint = Color.Blue
            ) {
                SettingsSwitchItem(
                    title = "プッシュ通知",
                    subtitle = "新しいメッセージや更新をお知らせします",
                    checked = notificationsEnabled,
                    onCheckedChange = { notificationsEnabled = it }
                )
                
                SettingsSwitchItem(
                    title = "メール通知",
                    subtitle = "重要な更新をメールでお知らせします",
                    checked = notificationsEnabled,
                    onCheckedChange = { notificationsEnabled = it }
                )
                
                SettingsSliderItem(
                    title = "通知音量",
                    subtitle = "通知音の音量を調整します",
                    value = 0.7f,
                    onValueChange = { /* 音量変更処理 */ }
                )
            }
        }
        
        // 表示設定
        item {
            SettingsSection(
                title = "表示設定",
                icon = Icons.Default.Visibility,
                iconTint = Color.Green
            ) {
                SettingsSwitchItem(
                    title = "ダークモード",
                    subtitle = "ダークテーマを使用します",
                    checked = darkModeEnabled,
                    onCheckedChange = { darkModeEnabled = it }
                )
                
                SettingsDropdownItem(
                    title = "フォントサイズ",
                    subtitle = "テキストのサイズを変更します",
                    currentValue = fontSize,
                    options = listOf("小", "中", "大", "特大"),
                    onOptionSelected = { fontSize = it }
                )
                
                SettingsDropdownItem(
                    title = "言語",
                    subtitle = "アプリの言語を変更します",
                    currentValue = language,
                    options = listOf("日本語", "English", "中文", "한국어"),
                    onOptionSelected = { language = it }
                )
            }
        }
        
        // データ設定
        item {
            SettingsSection(
                title = "データ設定",
                icon = Icons.Default.Storage,
                iconTint = Color(0xFFFF9800)
            ) {
                SettingsSwitchItem(
                    title = "自動保存",
                    subtitle = "変更を自動的に保存します",
                    checked = autoSaveEnabled,
                    onCheckedChange = { autoSaveEnabled = it }
                )
                
                SettingsButtonItem(
                    title = "キャッシュをクリア",
                    subtitle = "アプリのキャッシュデータを削除します",
                    buttonText = "クリア",
                    onClick = { /* キャッシュクリア処理 */ }
                )
                
                SettingsButtonItem(
                    title = "データをエクスポート",
                    subtitle = "アプリデータをファイルに保存します",
                    buttonText = "エクスポート",
                    onClick = { /* エクスポート処理 */ }
                )
            }
        }
        
        // セキュリティ設定
        item {
            SettingsSection(
                title = "セキュリティ",
                icon = Icons.Default.Security,
                iconTint = Color.Green
            ) {
                SettingsSwitchItem(
                    title = "生体認証",
                    subtitle = "指紋や顔認証でロックを解除します",
                    checked = false,
                    onCheckedChange = { /* 生体認証設定 */ }
                )
                
                SettingsButtonItem(
                    title = "パスワード変更",
                    subtitle = "アカウントのパスワードを変更します",
                    buttonText = "変更",
                    onClick = { /* パスワード変更処理 */ }
                )
                
                SettingsButtonItem(
                    title = "ログアウト",
                    subtitle = "すべてのデバイスからログアウトします",
                    buttonText = "ログアウト",
                    onClick = { /* ログアウト処理 */ },
                    buttonColor = Color.Red
                )
            }
        }
        
        // アプリ情報
        item {
            SettingsSection(
                title = "アプリ情報",
                icon = Icons.Default.Info,
                iconTint = Color.Gray
            ) {
                SettingsInfoItem("バージョン", "1.0.0")
                SettingsInfoItem("ビルド番号", "2024.01.01")
                SettingsInfoItem("開発者", "AI Bakusoku Test Team")
                
                SettingsButtonItem(
                    title = "利用規約",
                    subtitle = "アプリの利用規約を確認します",
                    buttonText = "表示",
                    onClick = { /* 利用規約表示 */ }
                )
                
                SettingsButtonItem(
                    title = "プライバシーポリシー",
                    subtitle = "プライバシーポリシーを確認します",
                    buttonText = "表示",
                    onClick = { /* プライバシーポリシー表示 */ }
                )
            }
        }
    }
}

@Composable
fun SettingsSection(
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
fun SettingsSwitchItem(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
fun SettingsSliderItem(
    title: String,
    subtitle: String,
    value: Float,
    onValueChange: (Float) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(8.dp))
        Slider(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsDropdownItem(
    title: String,
    subtitle: String,
    currentValue: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(8.dp))
        
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            OutlinedTextField(
                value = currentValue,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsButtonItem(
    title: String,
    subtitle: String,
    buttonText: String,
    onClick: () -> Unit,
    buttonColor: Color = MaterialTheme.colorScheme.primary
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
        ) {
            Text(buttonText)
        }
    }
}

@Composable
fun SettingsInfoItem(
    title: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    AibakusokutestTheme {
        SettingsScreen()
    }
} 