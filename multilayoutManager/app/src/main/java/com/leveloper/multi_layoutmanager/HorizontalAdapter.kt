package com.leveloper.multi_layoutmanager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leveloper.multi_layoutmanager.databinding.ItemHorizontalBinding

class HorizontalAdapter(private val items: List<String>) :
    RecyclerView.Adapter<HorizontalAdapter.HorizontalViewHolder>() {

    private lateinit var layoutInflater: LayoutInflater

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
        if (!::layoutInflater.isInitialized) {
            layoutInflater = LayoutInflater.from(parent.context)
        }

        val binding = ItemHorizontalBinding.inflate(layoutInflater, parent, false)
        return HorizontalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class HorizontalViewHolder(
        private val binding: ItemHorizontalBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(text: String) {
            binding.textView.text = text
        }
    }
}