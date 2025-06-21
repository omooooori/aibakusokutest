# AI Bakusoku Test

Cursorのルールファイル（mdc）を使って、チャットでコマンドを入力すると現在のコードに対するテストコード（単体テスト、UIテスト）を自動生成する機能をテストするためのサンプルAndroidアプリです。

## プロジェクト概要

このアプリは、Jetpack Composeを使用したAndroidアプリケーションで、以下の機能を提供します：

- **計算機機能**: 基本的な四則演算
- **ユーザー管理**: ユーザーの追加、表示、削除
- **フォーム検証**: 入力データのバリデーション
- **MVVMアーキテクチャ**: ViewModelを使用した状態管理

## アプリケーション構造

```
app/src/main/java/com/omooooori/ai_bakusoku_test/
├── MainActivity.kt                    # メインアクティビティ
├── MainViewModel.kt                   # MVVMアーキテクチャのビューモデル
├── User.kt                           # データクラスとリポジトリ
├── ui/
│   ├── components/                   # UIコンポーネント
│   │   ├── Greeting.kt              # 挨拶コンポーネント
│   │   ├── Calculator.kt            # 計算機コンポーネント
│   │   ├── UserInputForm.kt         # ユーザー入力フォーム
│   │   └── UserList.kt              # ユーザーリスト
│   ├── screens/                      # 画面
│   │   └── MainScreen.kt            # メイン画面
│   └── theme/                       # UIテーマ
└── utils/                           # ユーティリティ
    └── ValidationUtils.kt           # バリデーション関数
```

### 主要クラス

- **MainActivity**: Jetpack Composeを使用したメイン画面
- **MainViewModel**: MVVMアーキテクチャのビューモデル
- **UserRepository**: ユーザーデータの管理
- **CalculatorService**: 計算機能のビジネスロジック
- **User**: ユーザーデータクラス
- **CalculationResult**: 計算結果データクラス

### UIコンポーネント

- **Greeting**: 挨拶メッセージ表示
- **Calculator**: 計算機機能（数値入力、演算、結果表示）
- **UserInputForm**: ユーザー入力フォーム（名前、メール、年齢）
- **UserList**: ユーザーリスト表示（追加、削除機能）
- **MainScreen**: メイン画面の統合（全コンポーネントの組み合わせ）

## 機能詳細

### 1. 計算機機能
- 2つの数値と演算子（+, -, ×, ÷）を入力
- 計算結果の表示
- エラーハンドリング（ゼロ除算、無効な入力）

### 2. ユーザー管理機能
- ユーザー情報の入力（名前、メール、年齢）
- 入力データのバリデーション
- ユーザーリストの表示
- ユーザーの削除

### 3. 状態管理
- ViewModelを使用した状態管理
- 非同期処理の適切な処理
- エラーメッセージとサクセスメッセージの表示

### 4. UIコンポーネント設計
- 各機能を独立したComposable関数として分離
- 再利用可能なコンポーネント設計
- プレビュー機能による開発効率の向上

## テスト対象

このアプリは以下のテスト対象を提供します：

### 単体テスト対象
- `MainViewModel` - ビジネスロジックと状態管理
- `UserRepository` - データアクセス層
- `CalculatorService` - 計算ロジック
- `ValidationUtils` - バリデーション関数

### UIテスト対象
- `MainActivity` - メインアクティビティ
- `MainScreen` - メイン画面
- `Calculator` - 計算機コンポーネント
- `UserInputForm` - ユーザー入力フォーム
- `UserList` - ユーザーリスト
- `Greeting` - 挨拶コンポーネント

### Compose UIテスト対象
- 各Composable関数の個別テスト
- コンポーネント間の相互作用テスト
- プレビュー機能のテスト

## 使用方法

### 1. プロジェクトのビルド
```bash
./gradlew build
```

### 2. アプリの実行
```bash
./gradlew installDebug
```

### 3. テストの実行
```bash
# 単体テスト
./gradlew test

# UIテスト
./gradlew connectedAndroidTest
```

## Cursorルールファイルの使用方法

このプロジェクトには `.cursorrules` ファイルが含まれており、以下のコマンドでテストコードを自動生成できます：

### 単体テスト生成
```
「MainViewModelの単体テストを作成してください」
「UserRepositoryの単体テストを作成してください」
「ValidationUtilsの単体テストを作成してください」
```

### UIテスト生成
```
「MainActivityのUIテストを作成してください」
「CalculatorのUIテストを作成してください」
「UserInputFormのUIテストを作成してください」
「UserListのUIテストを作成してください」
```

### Compose UIテスト生成
```
「GreetingのCompose UIテストを作成してください」
「CalculatorのCompose UIテストを作成してください」
```

### 全テスト生成
```
「このアプリの全テストを作成してください」
```

## 技術スタック

- **言語**: Kotlin
- **UI**: Jetpack Compose
- **アーキテクチャ**: MVVM
- **状態管理**: ViewModel + StateFlow
- **非同期処理**: Coroutines
- **テスト**: JUnit 4, Espresso, Compose UI Testing

## 依存関係

- AndroidX Core KTX
- Jetpack Compose
- Material Design 3
- ViewModel
- Lifecycle
- Coroutines

## 開発環境

- Android Studio Hedgehog | 2023.1.1
- Kotlin 2.0.21
- Android Gradle Plugin 8.10.0
- Compose BOM 2024.09.00

## ファイル構造の特徴

### UIコンポーネントの分離
- 各機能を独立したComposable関数として実装
- `ui/components/` ディレクトリで管理
- 再利用可能で保守性の高い設計

### 画面レイアウト
- `ui/screens/` ディレクトリで画面全体のレイアウトを管理
- コンポーネントの組み合わせによる画面構築

### ユーティリティ関数
- `utils/` ディレクトリで共通機能を管理
- バリデーションや計算ロジックの分離

## ライセンス

このプロジェクトはテスト目的で作成されたサンプルアプリケーションです。

## 貢献

このプロジェクトはCursorのルールファイル機能のテスト用です。バグ報告や改善提案は歓迎します。 