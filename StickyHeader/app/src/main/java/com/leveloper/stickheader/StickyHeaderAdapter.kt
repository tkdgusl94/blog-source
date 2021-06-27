package com.leveloper.stickheader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leveloper.stickheader.databinding.Item1Binding
import com.leveloper.stickheader.databinding.Item2Binding

class StickyHeaderAdapter(
    private val items: List<Event>
) : RecyclerView.Adapter<StickyHeaderAdapter.StickyHeaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = StickyHeaderViewHolder(
        Item1Binding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: StickyHeaderViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class StickyHeaderViewHolder(
        private val binding: Item1Binding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(event: Event) {
            binding.title.text = event.value.toString()
            binding.date.text = "${event.date}월"

            binding.date.visibility = if (event.isHeader) View.VISIBLE else View.GONE
        }
    }

    fun isHeader(position: Int) = items[position].isHeader

    fun getHeaderView(list: RecyclerView, position: Int): View? {
        val item = items[position]

        val binding = Item2Binding.inflate(LayoutInflater.from(list.context), list, false)
        binding.date.text = "${item.date}월"
        return binding.root
    }
}