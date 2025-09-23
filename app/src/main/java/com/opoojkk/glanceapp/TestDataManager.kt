package com.opoojkk.glanceapp

import android.content.Context
import android.util.Log

/**
 * 测试数据管理器
 * 创建包含SharedPreferences支持的所有数据类型的测试数据
 */
object TestDataManager {
    
    private const val TAG = "TestDataManager"
    
    /**
     * 创建完整的测试数据集合
     * 包含SharedPreferences支持的所有数据类型：
     * - String
     * - Int
     * - Long
     * - Float
     * - Boolean
     * - StringSet
     */
    fun createComprehensiveTestData(context: Context) {
        Log.d(TAG, "开始创建完整的测试数据...")
        
        // 1. 用户配置文件 - 包含所有基本类型
        createUserProfileData(context)
        
        // 2. 应用设置文件 - 包含更多复杂数据
        createAppSettingsData(context)
        
        // 3. 缓存数据文件 - 包含大量数据
        createCacheData(context)
        
        // 4. 用户偏好文件 - 包含用户自定义设置
        createUserPreferencesData(context)
        
        // 5. 游戏数据文件 - 包含游戏相关数据
        createGameData(context)
        
        // 6. 网络配置文件 - 包含网络相关设置
        createNetworkConfigData(context)
        
        Log.d(TAG, "测试数据创建完成！")
    }
    
    /**
     * 创建用户配置文件数据
     */
    private fun createUserProfileData(context: Context) {
        val prefs = context.getSharedPreferences("user_profile", Context.MODE_PRIVATE)
        prefs.edit().apply {
            // String 类型
            putString("username", "张三")
            putString("email", "zhangsan@example.com")
            putString("phone", "+86 138 0013 8000")
            putString("address", "北京市朝阳区某某街道123号")
            putString("bio", "热爱编程的Android开发者")
            
            // Int 类型
            putInt("age", 28)
            putInt("level", 15)
            putInt("experience_points", 12500)
            putInt("login_count", 156)
            putInt("max_score", 9999)
            
            // Long 类型
            putLong("user_id", 123456789012345L)
            putLong("registration_time", System.currentTimeMillis() - 86400000 * 365) // 一年前
            putLong("last_login", System.currentTimeMillis())
            putLong("total_play_time", 3600000L) // 1小时，单位毫秒
            putLong("last_backup", System.currentTimeMillis() - 86400000) // 一天前
            
            // Float 类型
            putFloat("rating", 4.7f)
            putFloat("progress_percentage", 0.75f)
            putFloat("battery_level", 0.85f)
            putFloat("volume_level", 0.6f)
            putFloat("brightness", 0.8f)
            
            // Boolean 类型
            putBoolean("is_premium_user", true)
            putBoolean("email_verified", true)
            putBoolean("two_factor_enabled", false)
            putBoolean("auto_login", true)
            putBoolean("dark_mode_enabled", true)
            
            // StringSet 类型
            putStringSet("hobbies", setOf("编程", "阅读", "运动", "音乐", "旅行"))
            putStringSet("languages", setOf("中文", "English", "日本語"))
            putStringSet("skills", setOf("Android开发", "Kotlin", "Java", "React Native", "Flutter"))
            putStringSet("favorite_categories", setOf("技术", "科学", "历史", "文学"))
            putStringSet("blocked_users", setOf("spam_user_1", "spam_user_2"))
            
            apply()
        }
        Log.d(TAG, "用户配置文件数据创建完成")
    }
    
    /**
     * 创建应用设置数据
     */
    private fun createAppSettingsData(context: Context) {
        val prefs = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)
        prefs.edit().apply {
            // 主题和外观设置
            putString("theme", "dark")
            putString("primary_color", "#FF6200EE")
            putString("accent_color", "#FF03DAC5")
            putBoolean("use_system_theme", false)
            putBoolean("show_animations", true)
            
            // 通知设置
            putBoolean("push_notifications", true)
            putBoolean("email_notifications", false)
            putBoolean("sms_notifications", true)
            putInt("notification_sound", 1)
            putString("notification_ringtone", "default")
            
            // 隐私设置
            putBoolean("analytics_enabled", true)
            putBoolean("crash_reporting", true)
            putBoolean("location_tracking", false)
            putBoolean("data_collection", true)
            
            // 性能设置
            putInt("cache_size_mb", 100)
            putInt("max_concurrent_downloads", 3)
            putBoolean("auto_update", true)
            putBoolean("low_power_mode", false)
            
            // 语言和地区设置
            putString("language", "zh-CN")
            putString("country", "CN")
            putString("timezone", "Asia/Shanghai")
            putBoolean("use_24_hour_format", true)
            
            // 高级设置
            putFloat("animation_duration", 0.3f)
            putInt("max_retry_attempts", 3)
            putLong("session_timeout", 1800000L) // 30分钟
            putStringSet("enabled_features", setOf("feature_a", "feature_b", "feature_c"))
            
            apply()
        }
        Log.d(TAG, "应用设置数据创建完成")
    }
    
    /**
     * 创建缓存数据
     */
    private fun createCacheData(context: Context) {
        val prefs = context.getSharedPreferences("cache_data", Context.MODE_PRIVATE)
        prefs.edit().apply {
            // 缓存时间戳
            putLong("last_cache_update", System.currentTimeMillis())
            putLong("cache_expiry", System.currentTimeMillis() + 3600000L) // 1小时后过期
            
            // 缓存统计
            putInt("cache_hits", 1250)
            putInt("cache_misses", 45)
            putFloat("cache_hit_ratio", 0.965f)
            putInt("total_cached_items", 89)
            
            // 缓存配置
            putBoolean("cache_enabled", true)
            putInt("max_cache_size", 50)
            putString("cache_policy", "LRU")
            putBoolean("auto_cleanup", true)
            
            // 缓存内容标识
            putStringSet("cached_categories", setOf("news", "weather", "sports", "tech"))
            putStringSet("cache_tags", setOf("hot", "trending", "featured", "recommended"))
            
            // 性能指标
            putFloat("avg_load_time", 1.25f)
            putInt("memory_usage_mb", 45)
            putBoolean("compression_enabled", true)
            
            apply()
        }
        Log.d(TAG, "缓存数据创建完成")
    }
    
    /**
     * 创建用户偏好数据
     */
    private fun createUserPreferencesData(context: Context) {
        val prefs = context.getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        prefs.edit().apply {
            // 显示偏好
            putBoolean("show_tutorial", false)
            putBoolean("show_tips", true)
            putBoolean("compact_view", false)
            putInt("items_per_page", 20)
            putString("sort_order", "date_desc")
            
            // 内容偏好
            putStringSet("interested_topics", setOf("科技", "编程", "AI", "移动开发"))
            putStringSet("blocked_keywords", setOf("广告", "推广", "垃圾"))
            putBoolean("adult_content_filter", true)
            putBoolean("explicit_content_warning", true)
            
            // 交互偏好
            putBoolean("haptic_feedback", true)
            putBoolean("sound_effects", true)
            putFloat("haptic_intensity", 0.7f)
            putString("gesture_style", "modern")
            
            // 个性化设置
            putString("nickname", "编程小王子")
            putString("avatar_url", "https://example.com/avatar.jpg")
            putBoolean("profile_public", true)
            putString("status_message", "正在学习Android开发")
            
            // 时间偏好
            putInt("preferred_hours", 9) // 上午9点
            putString("timezone_offset", "+08:00")
            putBoolean("dst_enabled", true)
            
            apply()
        }
        Log.d(TAG, "用户偏好数据创建完成")
    }
    
    /**
     * 创建游戏数据
     */
    private fun createGameData(context: Context) {
        val prefs = context.getSharedPreferences("game_data", Context.MODE_PRIVATE)
        prefs.edit().apply {
            // 游戏进度
            putInt("current_level", 12)
            putInt("total_score", 45678)
            putInt("lives_remaining", 3)
            putInt("coins_collected", 1250)
            putFloat("completion_percentage", 0.65f)
            
            // 游戏设置
            putBoolean("sound_enabled", true)
            putBoolean("music_enabled", true)
            putFloat("sound_volume", 0.8f)
            putFloat("music_volume", 0.6f)
            putString("difficulty", "normal")
            
            // 成就系统
            putStringSet("unlocked_achievements", setOf("first_win", "level_10", "score_10000"))
            putStringSet("available_achievements", setOf("level_20", "score_50000", "perfect_game"))
            putInt("achievement_count", 3)
            
            // 游戏统计
            putLong("total_play_time", 7200000L) // 2小时
            putInt("games_played", 45)
            putInt("games_won", 32)
            putFloat("win_rate", 0.711f)
            putInt("best_streak", 8)
            
            // 游戏状态
            putBoolean("game_paused", false)
            putLong("last_save_time", System.currentTimeMillis())
            putString("current_character", "hero_1")
            putString("equipped_items", "sword,shield,armor")
            
            apply()
        }
        Log.d(TAG, "游戏数据创建完成")
    }
    
    /**
     * 创建网络配置数据
     */
    private fun createNetworkConfigData(context: Context) {
        val prefs = context.getSharedPreferences("network_config", Context.MODE_PRIVATE)
        prefs.edit().apply {
            // 网络设置
            putString("api_base_url", "https://api.example.com/v1")
            putString("cdn_url", "https://cdn.example.com")
            putInt("timeout_seconds", 30)
            putInt("retry_attempts", 3)
            putBoolean("use_https", true)
            
            // 代理设置
            putBoolean("proxy_enabled", false)
            putString("proxy_host", "")
            putInt("proxy_port", 0)
            putString("proxy_username", "")
            putBoolean("proxy_auth_required", false)
            
            // 缓存设置
            putInt("cache_duration_hours", 24)
            putBoolean("offline_mode", false)
            putString("sync_frequency", "hourly")
            putLong("last_sync", System.currentTimeMillis())
            
            // 安全设置
            putBoolean("certificate_pinning", true)
            putBoolean("ssl_verification", true)
            putString("encryption_method", "AES-256")
            putStringSet("allowed_hosts", setOf("api.example.com", "cdn.example.com"))
            
            // 性能设置
            putInt("max_concurrent_requests", 5)
            putBoolean("compression_enabled", true)
            putString("user_agent", "MyApp/1.0.0")
            putFloat("bandwidth_limit_mbps", 10.0f)
            
            apply()
        }
        Log.d(TAG, "网络配置数据创建完成")
    }
    
    /**
     * 清除所有测试数据
     */
    fun clearAllTestData(context: Context) {
        Log.d(TAG, "清除所有测试数据...")
        
        val fileNames = listOf(
            "user_profile",
            "app_settings", 
            "cache_data",
            "user_preferences",
            "game_data",
            "network_config"
        )
        
        fileNames.forEach { fileName ->
            val prefs = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
            prefs.edit().clear().apply()
            Log.d(TAG, "已清除 $fileName 数据")
        }
        
        Log.d(TAG, "所有测试数据已清除")
    }
    
    /**
     * 获取测试数据统计信息
     */
    fun getTestDataStats(context: Context): Map<String, Int> {
        val stats = mutableMapOf<String, Int>()
        
        val fileNames = listOf(
            "user_profile",
            "app_settings",
            "cache_data", 
            "user_preferences",
            "game_data",
            "network_config"
        )
        
        fileNames.forEach { fileName ->
            val prefs = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
            stats[fileName] = prefs.all.size
        }
        
        return stats
    }
}
