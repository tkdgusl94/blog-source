package com.leveloper.viewmodel_extensions

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider) {

    var isSaved = false

    fun saveDataOnIO() = onIO {
        delay(5000)
        isSaved = true
    }
}