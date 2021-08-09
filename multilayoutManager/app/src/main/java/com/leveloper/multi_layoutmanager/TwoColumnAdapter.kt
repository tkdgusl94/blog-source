package com.leveloper.multi_layoutmanager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leveloper.multi_layoutmanager.databinding.ItemTwoColumnBinding

class TwoColumnAdapter(private val items: List<String>) :
    RecyclerView.Adapter<TwoColumnAdapter.TwoColumnViewHolder>() {

    companion object {
        const val VIEW_TYPE = 2
    }

    private lateinit var layoutInflater: LayoutInflater

    override fun getItemViewType(position: Int) = VIEW_TYPE

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwoColumnViewHolder {
        if (!::layoutInflater.isInitialized) {
            layoutInflater = LayoutInflater.from(parent.context)
        }

        val binding = ItemTwoColumnBinding.inflate(layoutInflater, parent, false)
        return TwoColumnViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TwoColumnViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class TwoColumnViewHolder(
        private val binding: ItemTwoColumnBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(text: String) {
            binding.textView.text = text
        }
    }
}