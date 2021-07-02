package com.leveloper.treeadapter

class Node<T>(val content: T) {

    var parent: Node<T>? = null

    private val _children = mutableListOf<Node<T>>()
    val children: List<Node<T>>
        get() = _children

    var isExpand = true
        private set

    val isRoot: Boolean
        get() = parent == null

    val isLeaf: Boolean
        get() = children.isEmpty()

    private var _depth = UNDEFINE
    val depth: Int
        get() {
            if (isRoot)
                _depth = 0
            else if (_depth == UNDEFINE)
                _depth = parent!!.depth + 1
            return _depth
        }

    fun toggle() {
        isExpand = !isExpand
    }

    fun expand() {
        if (!isExpand) isExpand = true
    }

    fun expandAll() {
        expand()

        if (isLeaf) return
        children.forEach { it.expandAll() }
    }

    fun collapse() {
        if (isExpand) isExpand = false
    }

    fun collapseAll() {
        collapse()

        if (isLeaf) return
        children.forEach { it.collapseAll() }
    }

    fun addChild(child: Node<T>): Node<T> {
        _children.add(child)
        child.parent = this

        return this
    }

    companion object {
        private const val UNDEFINE = -1
    }
}