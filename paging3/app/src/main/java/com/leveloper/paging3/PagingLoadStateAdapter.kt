package com.leveloper.paging3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.leveloper.paging3.databinding.ItemLoadStateBinding

class PagingLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PagingLoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): PagingLoadStateViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PagingLoadStateViewHolder(ItemLoadStateBinding.inflate(layoutInflater, parent, false), retry)
    }

    override fun onBindViewHolder(holder: PagingLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}

class PagingLoadStateViewHolder(
    private val binding: ItemLoadStateBinding,
    private val retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(state: LoadState) {
        binding.retryButton.setOnClickListener { retry() }
        binding.isLoading = state is LoadState.Loading
        binding.isError = state is LoadState.Error
        binding.errorMessage = (state as? LoadState.Error)?.error?.message ?: ""
    }
}