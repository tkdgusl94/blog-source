package com.leveloper.sample.sample3

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.leveloper.sample.PageType
import com.leveloper.sample.R
import com.leveloper.sample.databinding.ActivitySampleOneBinding
import com.leveloper.sample.SampleFragment
import com.leveloper.sample.databinding.ActivitySampleThreeBinding
import java.lang.IllegalArgumentException

class SampleThreeActivity : AppCompatActivity() {

    private val viewModel: SampleThreeViewModel by viewModels()

    private val binding by lazy { ActivitySampleThreeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.vm = viewModel

        viewModel.currentPageType.observe(this) {
            changeFragment(it)
        }
    }

    private fun changeFragment(pageType: PageType) {
        val transaction = supportFragmentManager.beginTransaction()

        var targetFragment = supportFragmentManager.findFragmentByTag(pageType.tag)

        if (targetFragment == null) {
            targetFragment = getFragment(pageType)
            transaction.add(R.id.container, targetFragment, pageType.tag)
        }

        transaction.show(targetFragment)

        PageType.values()
            .filterNot { it == pageType }
            .forEach { type ->
                supportFragmentManager.findFragmentByTag(type.tag)?.let {
                    transaction.hide(it)
                }
            }

        transaction.commitAllowingStateLoss()
    }

    private fun getFragment(pageType: PageType): Fragment {
        return SampleFragment.newInstance(pageType.title)
    }
}