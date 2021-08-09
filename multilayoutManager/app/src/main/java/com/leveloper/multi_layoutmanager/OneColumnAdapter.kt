package com.leveloper.multi_layoutmanager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leveloper.multi_layoutmanager.databinding.ItemOneColumnBinding

class OneColumnAdapter(private val items: List<String>) :
    RecyclerView.Adapter<OneColumnAdapter.OneColumnViewHolder>() {

    companion object {
        const val VIEW_TYPE = 1
    }

    private lateinit var layoutInflater: LayoutInflater

    override fun getItemViewType(position: Int) = VIEW_TYPE

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OneColumnViewHolder {
        if (!::layoutInflater.isInitialized) {
            layoutInflater = LayoutInflater.from(parent.context)
        }

        val binding = ItemOneColumnBinding.inflate(layoutInflater, parent, false)
        return OneColumnViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OneColumnViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class OneColumnViewHolder(
        private val binding: ItemOneColumnBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(text: String) {
            binding.textView.text = text
        }
    }
}