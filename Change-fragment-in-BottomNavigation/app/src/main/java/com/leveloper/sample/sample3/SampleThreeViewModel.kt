package com.leveloper.sample.sample3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leveloper.sample.PageType
import com.leveloper.sample.R
import java.lang.IllegalArgumentException

class SampleThreeViewModel : ViewModel() {

    private val _currentPageType = MutableLiveData(PageType.PAGE1)
    val currentPageType: LiveData<PageType> = _currentPageType

    fun setCurrentPage(menuItemId: Int): Boolean {
        val pageType = getPageType(menuItemId)
        changeCurrentPage(pageType)

        return true
    }

    private fun getPageType(menuItemId: Int): PageType {
        return when (menuItemId) {
            R.id.item_page_1 -> PageType.PAGE1
            R.id.item_page_2 -> PageType.PAGE2
            R.id.item_page_3 -> PageType.PAGE3
            else -> throw IllegalArgumentException("not found menu item id")
        }
    }

    private fun changeCurrentPage(pageType: PageType) {
        if (currentPageType.value == pageType) return

        _currentPageType.value = pageType
    }
}