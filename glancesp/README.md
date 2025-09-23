# SharedPreferences Viewer Library

一个轻量级的 Android 库，用于查看和管理 SharedPreferences 数据。使用最少的三方库依赖，提供简洁的 API。

## 特性

- 🚀 **轻量级**: 只依赖 `androidx.core:core-ktx`，无其他三方库
- 📱 **简洁 API**: 提供简单易用的接口
- 🔍 **类型识别**: 自动识别数据类型（String、Int、Long、Float、Boolean、StringSet）
- 📂 **文件管理**: 支持查看所有 SharedPreferences 文件
- 🔑 **键值对查看**: 支持查看指定文件的所有键值对

## 依赖

```kotlin
dependencies {
    implementation("androidx.core:core-ktx:1.13.0")
}
```

## 使用方法

### 1. 获取所有 SharedPreferences 文件

```kotlin
val files = SharedPreferencesViewer.getAllSharedPreferencesFiles(context)
files.forEach { file ->
    println("文件名: ${file.fileName}")
    println("路径: ${file.filePath}")
    println("键数量: ${file.keyCount}")
}
```

### 2. 获取指定文件的所有键值对

```kotlin
val keyValuePairs = SharedPreferencesViewer.getKeyValuePairs(context, "user_settings")
keyValuePairs.forEach { pair ->
    println("键: ${pair.key}")
    println("值: ${pair.value}")
    println("类型: ${pair.type}")
}
```

### 3. 获取特定键值

```kotlin
val keyValue = SharedPreferencesViewer.getKeyValue(context, "user_settings", "username")
if (keyValue != null) {
    println("用户名: ${keyValue.value} (${keyValue.type})")
}
```

### 4. 检查文件是否存在

```kotlin
val exists = SharedPreferencesViewer.isSharedPreferencesFileExists(context, "user_settings")
if (exists) {
    println("文件存在")
}
```

## API 参考

### SharedPreferencesViewer

主要的工具类，提供静态方法。

#### 方法

- `getAllSharedPreferencesFiles(context: Context): List<SharedPrefsFileInfo>`
  - 获取所有 SharedPreferences 文件信息

- `getKeyValuePairs(context: Context, fileName: String): List<KeyValueInfo>`
  - 获取指定文件的所有键值对

- `getKeyValue(context: Context, fileName: String, key: String): KeyValueInfo?`
  - 获取特定键值，不存在返回 null

- `isSharedPreferencesFileExists(context: Context, fileName: String): Boolean`
  - 检查文件是否存在

### 数据类

#### SharedPrefsFileInfo
```kotlin
data class SharedPrefsFileInfo(
    val fileName: String,      // 文件名
    val filePath: String,      // 文件路径
    val keyCount: Int          // 键数量
)
```

#### KeyValueInfo
```kotlin
data class KeyValueInfo(
    val key: String,           // 键名
    val value: String,         // 值
    val type: String           // 类型 (String, Int, Long, Float, Boolean, StringSet, Unknown)
)
```

## 支持的数据类型

- **String**: 字符串
- **Int**: 整数
- **Long**: 长整数
- **Float**: 浮点数
- **Boolean**: 布尔值
- **StringSet**: 字符串集合
- **Unknown**: 其他类型

## 最小依赖

此库只依赖以下库：
- `androidx.core:core-ktx` - Android 核心扩展

无需其他三方库，保持轻量级。

## 版本信息

- **版本**: 1.0
- **最低 SDK**: 24 (Android 7.0)
- **目标 SDK**: 34 (Android 14)
- **Kotlin**: 1.9.24