package com.leveloper.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel>(@LayoutRes private val layoutResId: Int)
    : AppCompatActivity(layoutResId) {

    protected lateinit var binding: T
        private set

    protected abstract val viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)

        performDataBinding()
    }

    private fun performDataBinding() {
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    protected fun binding(action: T.() -> Unit) {
        binding.run(action)
    }
}