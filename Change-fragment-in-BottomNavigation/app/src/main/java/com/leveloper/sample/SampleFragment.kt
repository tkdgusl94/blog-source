package com.leveloper.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.leveloper.sample.databinding.FragmentSampleBinding

class SampleFragment : Fragment() {

    private val binding by lazy { FragmentSampleBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val title = requireArguments().getString("title")
        binding.textView.text = title

        return binding.root
    }

    companion object {
        fun newInstance(title: String) = SampleFragment().apply {
            arguments = Bundle().apply {
                putString("title", title)
            }
        }
    }
}