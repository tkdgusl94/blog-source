package com.leveloper.paging3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PagingViewModel @Inject constructor(
    private val repository: PagingRepository
) : ViewModel() {

    val pagingData = repository.getPagingData().map { pagingData ->
        pagingData.map<String, SampleModel> { SampleModel.Data(it) }
            .insertHeaderItem(item = SampleModel.Header("Header"))
            .insertFooterItem(item = SampleModel.Header("Footer"))
            .insertSeparators { before: SampleModel?, after: SampleModel? ->
                when {
                    before is SampleModel.Header || after is SampleModel.Header -> {
                        SampleModel.Separator
                    }
                    else -> {
                        null
                    }
                }
            }
    }.cachedIn(viewModelScope)
}