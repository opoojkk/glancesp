package com.opoojkk.glancesp

import android.content.Context
import android.content.Intent
import java.io.File

/**
 * SharedPreferences 查看器库
 * 提供简洁的 API 来查看和管理 SharedPreferences 数据
 */
object SharedPreferencesViewer {
    
    /**
     * 启动 SharedPreferences 查看器
     * @param context 上下文
     */
    fun startViewer(context: Context) {
        val intent = Intent(context, SharedPrefsListActivity::class.java)
        context.startActivity(intent)
    }
    
    /**
     * 启动 SharedPreferences 查看器并直接查看指定文件
     * @param context 上下文
     * @param fileName 要查看的 SharedPreferences 文件名
     */
    fun startViewer(context: Context, fileName: String) {
        val intent = Intent(context, KeyValueListActivity::class.java)
        intent.putExtra("file_name", fileName)
        context.startActivity(intent)
    }
    
    /**
     * 获取所有 SharedPreferences 文件信息
     * @param context 上下文
     * @return SharedPreferences 文件信息列表
     */
    fun getAllSharedPreferencesFiles(context: Context): List<SharedPrefsFileInfo> {
        val files = mutableListOf<SharedPrefsFileInfo>()
        val sharedPrefsDir = File(context.filesDir.parent, "shared_prefs")
        
        if (sharedPrefsDir.exists() && sharedPrefsDir.isDirectory) {
            sharedPrefsDir.listFiles()?.forEach { file ->
                if (file.name.endsWith(".xml")) {
                    val fileName = file.name.removeSuffix(".xml")
                    val keyCount = getKeyCount(context, fileName)
                    files.add(SharedPrefsFileInfo(fileName, file.absolutePath, keyCount))
                }
            }
        }
        return files.sortedBy { it.fileName }
    }
    
    /**
     * 获取指定 SharedPreferences 文件的所有键值对
     * @param context 上下文
     * @param fileName SharedPreferences 文件名
     * @return 键值对信息列表
     */
    fun getKeyValuePairs(context: Context, fileName: String): List<KeyValueInfo> {
        val pairs = mutableListOf<KeyValueInfo>()
        try {
            val prefs = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
            prefs.all.forEach { (key, value) ->
                val type = when (value) {
                    is String -> "String"
                    is Int -> "Int"
                    is Long -> "Long"
                    is Float -> "Float"
                    is Boolean -> "Boolean"
                    is Set<*> -> "StringSet"
                    else -> "Unknown"
                }
                pairs.add(KeyValueInfo(key, value.toString(), type))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return pairs.sortedBy { it.key }
    }
    
    /**
     * 获取指定 SharedPreferences 文件中的特定键值
     * @param context 上下文
     * @param fileName SharedPreferences 文件名
     * @param key 键名
     * @return 键值信息，如果不存在返回 null
     */
    fun getKeyValue(context: Context, fileName: String, key: String): KeyValueInfo? {
        return try {
            val prefs = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
            val value = prefs.all[key] ?: return null
            val type = when (value) {
                is String -> "String"
                is Int -> "Int"
                is Long -> "Long"
                is Float -> "Float"
                is Boolean -> "Boolean"
                is Set<*> -> "StringSet"
                else -> "Unknown"
            }
            KeyValueInfo(key, value.toString(), type)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    
    /**
     * 检查 SharedPreferences 文件是否存在
     * @param context 上下文
     * @param fileName SharedPreferences 文件名
     * @return 文件是否存在
     */
    fun isSharedPreferencesFileExists(context: Context, fileName: String): Boolean {
        val sharedPrefsDir = File(context.filesDir.parent, "shared_prefs")
        val file = File(sharedPrefsDir, "$fileName.xml")
        return file.exists()
    }
    
    private fun getKeyCount(context: Context, fileName: String): Int {
        return try {
            val prefs = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
            prefs.all.size
        } catch (e: Exception) {
            0
        }
    }
}

/**
 * SharedPreferences 文件信息
 */
data class SharedPrefsFileInfo(
    val fileName: String,
    val filePath: String,
    val keyCount: Int
)

/**
 * 键值对信息
 */
data class KeyValueInfo(
    val key: String,
    val value: String,
    val type: String
)
