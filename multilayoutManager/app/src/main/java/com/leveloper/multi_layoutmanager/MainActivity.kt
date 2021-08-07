package com.leveloper.multi_layoutmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leveloper.multi_layoutmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val oneColumnAdapter by lazy {
        val items = listOf("A1", "A2", "A3", "A4")
        OneColumnAdapter(items)
    }

    private val twoColumnAdapter by lazy {
        val items = listOf("C1", "C2", "C3", "C4", "C5", "C6")
        TwoColumnAdapter(items)
    }

    private val horizontalWrapperAdapter by lazy {
        val items = listOf("B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9")
        HorizontalWrapperAdapter(items)
    }

    private val concatAdapter: ConcatAdapter by lazy {
        val config = ConcatAdapter.Config.Builder()
            .setIsolateViewTypes(false)
            .build()
        ConcatAdapter(config, oneColumnAdapter, horizontalWrapperAdapter, twoColumnAdapter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.adapter = concatAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2).apply {
            spanSizeLookup = object: GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    println("type: ${concatAdapter.getItemViewType(position)}")
                    return when (concatAdapter.getItemViewType(position)) {
                        OneColumnAdapter.VIEW_TYPE -> 2
                        TwoColumnAdapter.VIEW_TYPE -> 1
                        HorizontalWrapperAdapter.VIEW_TYPE -> 2
                        else -> 1
                    }
                }
            }
        }
    }
}