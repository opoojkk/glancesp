package com.opoojkk.glancesp

import android.content.Context

/**
 * SharedPreferencesViewer 使用示例
 * 展示如何使用库的各种功能
 */
class ExampleUsage {
    
    fun demonstrateUsage(context: Context) {
        // 1. 获取所有 SharedPreferences 文件
        val allFiles = SharedPreferencesViewer.getAllSharedPreferencesFiles(context)
        println("找到 ${allFiles.size} 个 SharedPreferences 文件:")
        allFiles.forEach { file ->
            println("- ${file.fileName}: ${file.keyCount} 个键")
        }
        
        // 2. 检查特定文件是否存在
        val fileName = "user_settings"
        if (SharedPreferencesViewer.isSharedPreferencesFileExists(context, fileName)) {
            println("\n文件 '$fileName' 存在")
            
            // 3. 获取文件中的所有键值对
            val keyValuePairs = SharedPreferencesViewer.getKeyValuePairs(context, fileName)
            println("文件 '$fileName' 包含 ${keyValuePairs.size} 个键值对:")
            keyValuePairs.forEach { pair ->
                println("  ${pair.key}: ${pair.value} (${pair.type})")
            }
            
            // 4. 获取特定键的值
            val username = SharedPreferencesViewer.getKeyValue(context, fileName, "username")
            if (username != null) {
                println("\n用户名: ${username.value}")
            }
        } else {
            println("\n文件 '$fileName' 不存在")
        }
    }
    
    /**
     * 创建测试数据的示例
     */
    fun createTestData(context: Context) {
        // 创建一些测试数据
        val prefs = context.getSharedPreferences("user_settings", Context.MODE_PRIVATE)
        prefs.edit().apply {
            putString("username", "testuser")
            putString("email", "test@example.com")
            putInt("age", 25)
            putBoolean("notifications_enabled", true)
            putFloat("rating", 4.5f)
            putLong("last_login", System.currentTimeMillis())
            putStringSet("tags", setOf("android", "kotlin", "development"))
            apply()
        }
        
        println("测试数据已创建")
    }
}
