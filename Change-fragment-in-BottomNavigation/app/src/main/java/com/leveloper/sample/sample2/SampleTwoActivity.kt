package com.leveloper.sample.sample2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.leveloper.sample.PageType
import com.leveloper.sample.R
import com.leveloper.sample.SampleFragment
import com.leveloper.sample.databinding.ActivitySampleTwoBinding
import java.lang.IllegalArgumentException

class SampleTwoActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySampleTwoBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bnvMain.setOnItemSelectedListener {
            val pageType = getPageType(it.itemId)
            changeFragment(pageType)
            true
        }

        changeFragment(PageType.PAGE1)
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

    private fun getPageType(menuItemId: Int): PageType {
        return when (menuItemId) {
            R.id.item_page_1 -> PageType.PAGE1
            R.id.item_page_2 -> PageType.PAGE2
            R.id.item_page_3 -> PageType.PAGE3
            else -> throw IllegalArgumentException("not found menu item id")
        }
    }

    private fun getFragment(pageType: PageType): Fragment {
        return SampleFragment.newInstance(pageType.title)
    }
}