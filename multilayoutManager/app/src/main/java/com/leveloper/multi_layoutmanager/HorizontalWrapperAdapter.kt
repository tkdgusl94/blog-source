package com.leveloper.multi_layoutmanager

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leveloper.multi_layoutmanager.databinding.ItemHorizontalWrapperBinding

class HorizontalWrapperAdapter(
    private val items: List<String>
) : RecyclerView.Adapter<HorizontalWrapperAdapter.HorizontalWrapperViewHolder>() {

    companion object {
        const val VIEW_TYPE = 4
    }

    private lateinit var layoutInflater: LayoutInflater

    override fun getItemViewType(position: Int) = VIEW_TYPE

    override fun getItemCount() = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalWrapperViewHolder {
        if (!::layoutInflater.isInitialized) {
            layoutInflater = LayoutInflater.from(parent.context)
        }

        val binding = ItemHorizontalWrapperBinding.inflate(layoutInflater, parent, false)
        initRecyclerView(parent.context, binding.recyclerView)

        return HorizontalWrapperViewHolder(binding)
    }

    private fun initRecyclerView(context: Context, recyclerView: RecyclerView) {
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = HorizontalAdapter(items)
    }

    override fun onBindViewHolder(holder: HorizontalWrapperViewHolder, position: Int) {
        // no-op
    }

    inner class HorizontalWrapperViewHolder(binding: ItemHorizontalWrapperBinding) :
        RecyclerView.ViewHolder(binding.root)
}