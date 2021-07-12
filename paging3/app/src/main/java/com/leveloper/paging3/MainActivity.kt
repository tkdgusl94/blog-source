package com.leveloper.paging3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: PagingViewModel by viewModels()

    private val adapter: PagingAdapter by lazy { PagingAdapter() }
    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerView) }
    private val swipeRefreshLayout: SwipeRefreshLayout by lazy { findViewById(R.id.swipeRefreshLayout) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            PagingLoadStateAdapter { adapter.retry() },
            PagingLoadStateAdapter { adapter.retry() }
        )

        lifecycleScope.launch {
            viewModel.pagingData.collectLatest {
                adapter.submitData(it)
            }
        }

        swipeRefreshLayout.setOnRefreshListener {
            // refresh
            adapter.refresh()
            swipeRefreshLayout.isRefreshing = false
        }
    }
}