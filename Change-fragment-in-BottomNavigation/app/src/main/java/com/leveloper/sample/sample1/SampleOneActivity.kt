package com.leveloper.sample.sample1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.leveloper.sample.R
import com.leveloper.sample.databinding.ActivitySampleOneBinding
import com.leveloper.sample.SampleFragment
import java.lang.IllegalArgumentException

class SampleOneActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySampleOneBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bnvMain.setOnItemSelectedListener {
            changeFragment(it.itemId)
            true
        }

        // init fragment
        changeFragment(R.id.item_page_1)
    }

    private fun changeFragment(menuItemId: Int) {
        val targetFragment = getFragment(menuItemId)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, targetFragment)
            .commitAllowingStateLoss()
    }

    private fun getFragment(menuItemId: Int): Fragment {
        val title = when (menuItemId) {
            R.id.item_page_1 -> "page1"
            R.id.item_page_2 -> "page2"
            R.id.item_page_3 -> "page3"
            else -> throw IllegalArgumentException("not found menu item id")
        }
        return SampleFragment.newInstance(title)
    }
}