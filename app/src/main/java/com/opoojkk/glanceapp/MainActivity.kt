package com.opoojkk.glanceapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.opoojkk.glancesp.SharedPreferencesViewer

class MainActivity : AppCompatActivity() {
    
    private lateinit var statusText: TextView
    private lateinit var createDataButton: Button
    private lateinit var viewDataButton: Button
    private lateinit var clearDataButton: Button
    private lateinit var statsButton: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        initViews()
        setupClickListeners()
        updateStatus()
    }
    
    private fun initViews() {
        statusText = findViewById(R.id.status_text)
        createDataButton = findViewById(R.id.create_data_button)
        viewDataButton = findViewById(R.id.view_data_button)
        clearDataButton = findViewById(R.id.clear_data_button)
        statsButton = findViewById(R.id.stats_button)
    }
    
    private fun setupClickListeners() {
        createDataButton.setOnClickListener {
            createTestData()
        }
        
        viewDataButton.setOnClickListener {
            viewTestData()
        }
        
        clearDataButton.setOnClickListener {
            clearTestData()
        }
        
        statsButton.setOnClickListener {
            showDataStats()
        }
    }
    
    private fun createTestData() {
        Log.d("MainActivity", "开始创建测试数据...")
        statusText.text = "正在创建测试数据..."
        
        try {
            TestDataManager.createComprehensiveTestData(this)
            statusText.text = "测试数据创建成功！\n包含6个SharedPreferences文件，涵盖所有数据类型"
            Log.d("MainActivity", "测试数据创建完成")
        } catch (e: Exception) {
            statusText.text = "创建测试数据时出错: ${e.message}"
            Log.e("MainActivity", "创建测试数据失败", e)
        }
    }
    
    private fun viewTestData() {
        Log.d("MainActivity", "启动SharedPreferences查看器...")
        try {
            SharedPreferencesViewer.startViewer(this)
            statusText.text = "已启动SharedPreferences查看器"
        } catch (e: Exception) {
            statusText.text = "启动查看器时出错: ${e.message}"
            Log.e("MainActivity", "启动查看器失败", e)
        }
    }
    
    private fun clearTestData() {
        Log.d("MainActivity", "清除测试数据...")
        statusText.text = "正在清除测试数据..."
        
        try {
            TestDataManager.clearAllTestData(this)
            statusText.text = "所有测试数据已清除"
            Log.d("MainActivity", "测试数据清除完成")
        } catch (e: Exception) {
            statusText.text = "清除测试数据时出错: ${e.message}"
            Log.e("MainActivity", "清除测试数据失败", e)
        }
    }
    
    private fun showDataStats() {
        Log.d("MainActivity", "显示数据统计...")
        
        try {
            val stats = TestDataManager.getTestDataStats(this)
            val totalKeys = stats.values.sum()
            
            val statsText = buildString {
                appendLine("数据统计信息:")
                appendLine("总文件数: ${stats.size}")
                appendLine("总键值对数: $totalKeys")
                appendLine()
                appendLine("各文件详情:")
                stats.forEach { (fileName, keyCount) ->
                    appendLine("• $fileName: $keyCount 个键值对")
                }
            }
            
            statusText.text = statsText
            Log.d("MainActivity", "数据统计显示完成")
        } catch (e: Exception) {
            statusText.text = "获取统计信息时出错: ${e.message}"
            Log.e("MainActivity", "获取统计信息失败", e)
        }
    }
    
    private fun updateStatus() {
        val stats = TestDataManager.getTestDataStats(this)
        val totalKeys = stats.values.sum()
        
        if (totalKeys > 0) {
            statusText.text = "当前有 $totalKeys 个测试数据键值对\n点击按钮进行操作"
        } else {
            statusText.text = "暂无测试数据\n点击'创建测试数据'开始"
        }
    }
}