package com.leveloper.treeadapter

import android.view.View

fun View.setOnSingleClickListener(onSingleClick: (View) -> Unit) {
    var lastClickTime = System.currentTimeMillis()

    setOnClickListener {
        if (System.currentTimeMillis() - lastClickTime < 500) return@setOnClickListener

        lastClickTime = System.currentTimeMillis()

        onSingleClick(this)
    }
}