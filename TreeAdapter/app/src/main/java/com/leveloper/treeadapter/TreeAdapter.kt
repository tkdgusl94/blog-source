package com.leveloper.treeadapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class TreeAdapter<T, VH: TreeViewHolder<T>>(
    nodes: List<Node<T>> = emptyList()
) : RecyclerView.Adapter<VH>() {

    protected val displayNodes = mutableListOf<Node<T>>()

    init {
        setDisplayNodes(nodes)
    }

    override fun getItemCount() = displayNodes.size

    private fun setDisplayNodes(nodes: List<Node<T>>) {
        nodes.forEach { node ->
            displayNodes.add(node)
            if (!node.isLeaf && node.isExpand) {
                setDisplayNodes(node.children)
            }
        }
    }

    open fun toggle(node: Node<T>) {
        if (node.isLeaf) return

        val isExpand = node.isExpand
        val position = displayNodes.indexOf(node)

        if (isExpand)
            notifyItemRangeRemoved(position + 1, removeChildNodes(node, true))
        else
            notifyItemRangeInserted(position + 1, addChildNodes(node, position + 1))

        notifyItemChanged(position)
    }

    fun replaceAll(nodes: List<Node<T>>) {
        displayNodes.clear()
        setDisplayNodes(nodes)
        notifyDataSetChanged()
    }

    private fun addChildNodes(parent: Node<T>, startIndex: Int): Int {
        val childList = parent.children
        var addChildCount = 0

        childList.forEach { child ->
            displayNodes.add(startIndex + addChildCount++, child)
            if (child.isExpand) {
                addChildCount += addChildNodes(child, startIndex + addChildCount)
            }
        }

        if (!parent.isExpand) parent.toggle()

        return addChildCount
    }

    private fun removeChildNodes(parent: Node<T>, shouldToggle: Boolean = true): Int {
        if (parent.isLeaf) return 0

        val childList = parent.children
        var removeChildCount = childList.size
        displayNodes.removeAll(childList)

        childList.forEach { child ->
            if (child.isExpand) {
                child.toggle()
                removeChildCount += removeChildNodes(child, false)
            }
        }

        if (shouldToggle) parent.toggle()
        return removeChildCount
    }
}