package com.opoojkk.glancesp

import android.content.Context
import android.content.SharedPreferences

object TestDataHelper {
    
    fun createTestData(context: Context) {
        // 创建一些测试用的SharedPreferences数据
        val prefs1 = context.getSharedPreferences("user_settings", Context.MODE_PRIVATE)
        prefs1.edit().apply {
            putString("username", "testuser")
            putString("email", "test@example.com")
            putInt("age", 25)
            putBoolean("notifications_enabled", true)
            putFloat("rating", 4.5f)
            putLong("last_login", System.currentTimeMillis())
            putStringSet("tags", setOf("android", "kotlin", "development"))
            // 添加长内容测试数据
            putString("long_description", "This is a very long description that contains a lot of text to test the horizontal scrolling functionality. It should demonstrate how the text can be scrolled horizontally when it exceeds the width of the screen. This text is intentionally made very long to test the scrolling behavior and text selection capabilities.")
            putString("json_data", "{\"name\":\"John Doe\",\"age\":30,\"email\":\"john.doe@example.com\",\"address\":{\"street\":\"123 Main Street\",\"city\":\"New York\",\"state\":\"NY\",\"zipCode\":\"10001\"},\"phoneNumbers\":[\"+1-555-123-4567\",\"+1-555-987-6543\"],\"isActive\":true,\"lastLogin\":\"2024-01-15T10:30:00Z\"}")
            putString("base64_encoded_data", "SGVsbG8gV29ybGQhIFRoaXMgaXMgYSB2ZXJ5IGxvbmcgQmFzZTY0IGVuY29kZWQgc3RyaW5nIHRoYXQgc2hvdWxkIGRlbW9uc3RyYXRlIHRoZSBob3Jpem9udGFsIHNjcm9sbGluZyBmdW5jdGlvbmFsaXR5LiBJdCBjb250YWlucyBtdWx0aXBsZSBsaW5lcyBvZiB0ZXh0IHRoYXQgd2lsbCBleGNlZWQgdGhlIHdpZHRoIG9mIHRoZSBzY3JlZW4u")
            apply()
        }
        
        val prefs2 = context.getSharedPreferences("app_config", Context.MODE_PRIVATE)
        prefs2.edit().apply {
            putString("theme", "dark")
            putString("language", "en")
            putBoolean("debug_mode", false)
            putInt("max_retries", 3)
            putLong("cache_size", 1024 * 1024) // 1MB
            putFloat("version", 1.0f)
            apply()
        }
        
        val prefs3 = context.getSharedPreferences("game_stats", Context.MODE_PRIVATE)
        prefs3.edit().apply {
            putInt("level", 15)
            putInt("score", 12500)
            putBoolean("sound_enabled", true)
            putString("player_name", "Gamer123")
            putLong("play_time", 3600000) // 1 hour in milliseconds
            putFloat("difficulty", 0.8f)
            putStringSet("achievements", setOf("first_win", "level_10", "high_score"))
            apply()
        }
    }
}
