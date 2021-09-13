package com.leveloper.shimmereffect

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leveloper.shimmereffect.databinding.LayoutItemBinding

class SampleAdapter : RecyclerView.Adapter<SampleAdapter.SampleViewHolder>() {

    private val samples = mutableListOf<Sample>()

    private lateinit var layoutInflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        if (!::layoutInflater.isInitialized) {
            layoutInflater = LayoutInflater.from(parent.context)
        }

        return SampleViewHolder(LayoutItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.bind(samples[position])
    }

    override fun getItemCount() = samples.size

    @SuppressLint("NotifyDataSetChanged")
    fun replaceAll(samples: List<Sample>) {
        this.samples.clear()
        this.samples.addAll(samples)

        notifyDataSetChanged()
    }

    inner class SampleViewHolder(private val binding: LayoutItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(sample: Sample) {
            binding.tvName.text = sample.name
            binding.tvEmail.text = sample.email
        }
    }
}