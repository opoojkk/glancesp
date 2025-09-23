package com.opoojkk.glancesp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.opoojkk.glancesp.databinding.ActivityKeyValueListBinding

/**
 * 键值对详情 Activity
 * 显示指定 SharedPreferences 文件的所有键值对
 */
class KeyValueListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKeyValueListBinding
    private lateinit var adapter: KeyValueAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKeyValueListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fileName = intent.getStringExtra("file_name") ?: ""
        supportActionBar?.title = fileName

        setupRecyclerView()
        loadKeyValuePairs(fileName)
    }

    private fun setupRecyclerView() {
        adapter = KeyValueAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun loadKeyValuePairs(fileName: String) {
        val keyValuePairs = getKeyValuePairs(fileName)
        if (keyValuePairs.isEmpty()) {
            binding.emptyText.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        } else {
            binding.emptyText.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
            adapter.submitList(keyValuePairs)
        }
    }

    private fun getKeyValuePairs(fileName: String): List<KeyValueInfo> {
        val pairs = mutableListOf<KeyValueInfo>()
        try {
            val prefs = getSharedPreferences(fileName, Context.MODE_PRIVATE)
            prefs.all.forEach { (key, value) ->
                val type = when (value) {
                    is String -> getString(R.string.type_string)
                    is Int -> getString(R.string.type_int)
                    is Long -> getString(R.string.type_long)
                    is Float -> getString(R.string.type_float)
                    is Boolean -> getString(R.string.type_boolean)
                    is Set<*> -> getString(R.string.type_stringset)
                    else -> getString(R.string.type_unknown)
                }
                pairs.add(KeyValueInfo(key, value.toString(), type))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return pairs.sortedBy { it.key }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    data class KeyValueInfo(
        val key: String,
        val value: String,
        val type: String
    )

    private class KeyValueAdapter : RecyclerView.Adapter<KeyValueAdapter.ViewHolder>() {

        private var keyValuePairs = listOf<KeyValueInfo>()

        fun submitList(newPairs: List<KeyValueInfo>) {
            keyValuePairs = newPairs
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_key_value, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val pair = keyValuePairs[position]
            holder.bind(pair)
        }

        override fun getItemCount(): Int = keyValuePairs.size

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val keyValue: TextView = itemView.findViewById(R.id.key_value)
            private val valueText: TextView = itemView.findViewById(R.id.value_text)
            private val typeValue: TextView = itemView.findViewById(R.id.type_value)

            fun bind(pair: KeyValueInfo) {
                keyValue.text = pair.key
                valueText.text = pair.value
                typeValue.text = pair.type

                // 设置长按复制功能
                keyValue.setOnLongClickListener {
                    copyToClipboard(keyValue.context, keyValue.context.getString(R.string.key_copied), pair.key)
                    true
                }

                valueText.setOnLongClickListener {
                    copyToClipboard(valueText.context, valueText.context.getString(R.string.value_copied), pair.value)
                    true
                }
            }

            private fun copyToClipboard(context: Context, label: String, text: String) {
                val clipboard =
                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText(label, text)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(context, label, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
