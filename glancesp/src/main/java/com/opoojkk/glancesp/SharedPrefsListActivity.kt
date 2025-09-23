package com.opoojkk.glancesp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.opoojkk.glancesp.databinding.ActivitySharedPrefsListBinding
import java.io.File

/**
 * SharedPreferences 文件列表 Activity
 * 作为 glancesp 库的入口 Activity
 */
class SharedPrefsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySharedPrefsListBinding
    private lateinit var adapter: SharedPrefsFileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPrefsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.shared_prefs_list_title)

        setupRecyclerView()
        loadSharedPrefsFiles()
    }

    private fun setupRecyclerView() {
        adapter = SharedPrefsFileAdapter { fileName ->
            val intent = Intent(this, KeyValueListActivity::class.java)
            intent.putExtra("file_name", fileName)
            startActivity(intent)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun loadSharedPrefsFiles() {
        val sharedPrefsFiles = findSharedPrefsFiles()
        if (sharedPrefsFiles.isEmpty()) {
            binding.emptyText.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        } else {
            binding.emptyText.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
            adapter.submitList(sharedPrefsFiles)
        }
    }

    private fun findSharedPrefsFiles(): List<SharedPrefsFileInfo> {
        val files = mutableListOf<SharedPrefsFileInfo>()
        val sharedPrefsDir = File(filesDir.parent, "shared_prefs")

        if (sharedPrefsDir.exists() && sharedPrefsDir.isDirectory) {
            sharedPrefsDir.listFiles()?.forEach { file ->
                if (file.name.endsWith(".xml")) {
                    val fileName = file.name.removeSuffix(".xml")
                    val keyCount = getKeyCount(fileName)
                    files.add(SharedPrefsFileInfo(fileName, file.absolutePath, keyCount))
                }
            }
        }
        return files.sortedBy { it.fileName }
    }

    private fun getKeyCount(fileName: String): Int {
        return try {
            val prefs = getSharedPreferences(fileName, Context.MODE_PRIVATE)
            prefs.all.size
        } catch (e: Exception) {
            0
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    data class SharedPrefsFileInfo(
        val fileName: String,
        val filePath: String,
        val keyCount: Int
    )

    private class SharedPrefsFileAdapter(
        private val onItemClick: (String) -> Unit
    ) : RecyclerView.Adapter<SharedPrefsFileAdapter.ViewHolder>() {

        private var files = listOf<SharedPrefsFileInfo>()

        fun submitList(newFiles: List<SharedPrefsFileInfo>) {
            files = newFiles
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_shared_prefs_file, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val file = files[position]
            holder.bind(file)
        }

        override fun getItemCount(): Int = files.size

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val fileName: TextView = itemView.findViewById(R.id.file_name)
            private val filePath: TextView = itemView.findViewById(R.id.file_path)
            private val keyCount: TextView = itemView.findViewById(R.id.key_count)

            fun bind(file: SharedPrefsFileInfo) {
                fileName.text = file.fileName
                filePath.text = file.filePath
                keyCount.text = itemView.context.getString(R.string.keys_count, file.keyCount)

                itemView.setOnClickListener {
                    onItemClick(file.fileName)
                }
            }
        }
    }
}
