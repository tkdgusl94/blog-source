package com.leveloper.treeadapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class TreeViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    protected val context = itemView.context

    abstract fun bind(data: Node<T>)
}