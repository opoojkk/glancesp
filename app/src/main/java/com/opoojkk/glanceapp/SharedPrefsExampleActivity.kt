package com.opoojkk.glanceapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.opoojkk.glancesp.SharedPreferencesViewer

/**
 * 展示如何使用 glancesp 库的示例 Activity
 */
class SharedPrefsExampleActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 创建一些测试数据
        createTestData()

        // 使用 glancesp 库查看 SharedPreferences 数据
        demonstrateLibraryUsage()
    }
    
    private fun createTestData() {
        // 创建一些测试用的 SharedPreferences 数据
        val prefs = getSharedPreferences("user_settings", MODE_PRIVATE)
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
        
        val appConfig = getSharedPreferences("app_config", MODE_PRIVATE)
        appConfig.edit().apply {
            putString("theme", "dark")
            putString("language", "en")
            putBoolean("debug_mode", false)
            putInt("max_retries", 3)
            apply()
        }
    }
    
    private fun demonstrateLibraryUsage() {
        Log.d("SharedPrefsExample", "=== 使用 glancesp 库查看 SharedPreferences 数据 ===")

        // 1. 启动 SharedPreferences 查看器（UI 方式）
        Log.d("SharedPrefsExample", "启动 SharedPreferences 查看器...")
        SharedPreferencesViewer.startViewer(this)

        // 2. 或者直接查看特定文件
        // SharedPreferencesViewer.startViewer(this, "user_settings")

        // 3. 编程方式获取数据
        val allFiles = SharedPreferencesViewer.getAllSharedPreferencesFiles(this)
        Log.d("SharedPrefsExample", "找到 ${allFiles.size} 个 SharedPreferences 文件:")
        allFiles.forEach { file ->
            Log.d("SharedPrefsExample", "- ${file.fileName}: ${file.keyCount} 个键")
        }

        // 4. 查看特定文件的所有键值对
        val keyValuePairs = SharedPreferencesViewer.getKeyValuePairs(this, "user_settings")
        Log.d("SharedPrefsExample", "user_settings 文件包含 ${keyValuePairs.size} 个键值对:")
        keyValuePairs.forEach { pair ->
            Log.d("SharedPrefsExample", "  ${pair.key}: ${pair.value} (${pair.type})")
        }

        // 5. 获取特定键的值
        val username = SharedPreferencesViewer.getKeyValue(this, "user_settings", "username")
        if (username != null) {
            Log.d("SharedPrefsExample", "用户名: ${username.value}")
        }

        // 6. 检查文件是否存在
        val exists = SharedPreferencesViewer.isSharedPreferencesFileExists(this, "user_settings")
        Log.d("SharedPrefsExample", "user_settings 文件存在: $exists")
    }
}
