package com.leveloper.paging3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PagingViewModel @Inject constructor(
    private val repository: PagingRepository
) : ViewModel() {

    val pagingData = repository.getPagingData().cachedIn(viewModelScope)
}