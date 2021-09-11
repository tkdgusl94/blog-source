package com.leveloper.viewmodel_extensions

import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainViewModelTest : TestCase() {

    var mainCoroutineRule = MainCoroutineRule()

    val mainViewModel = MainViewModel(
        TestDispatcherProvider(mainCoroutineRule.testCoroutineDispatcher)
    )

    fun test01_save_data() {
        mainCoroutineRule.runBlockingTest {
            mainViewModel.saveDataOnIO()
            advanceUntilIdle()

            assertTrue(mainViewModel.isSaved)
        }
        mainCoroutineRule.testCoroutineDispatcher.cleanupTestCoroutines()
    }
}