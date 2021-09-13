package com.leveloper.shimmereffect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.leveloper.shimmereffect.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.rvSample.adapter = SampleAdapter()

        loadSampleData()
    }

    private fun loadSampleData() {
        lifecycleScope.launch {
            showSampleData(isLoading = true)

            delay(3000)

            val samples = getSampleList()
            (binding.rvSample.adapter as SampleAdapter).replaceAll(samples)

            showSampleData(isLoading = false)
        }
    }

    private fun showSampleData(isLoading: Boolean) {
        if (isLoading) {
            binding.sflSample.startShimmer()
            binding.sflSample.visibility = View.VISIBLE
            binding.rvSample.visibility = View.GONE
        } else {
            binding.sflSample.stopShimmer()
            binding.sflSample.visibility = View.GONE
            binding.rvSample.visibility = View.VISIBLE
        }
    }
}