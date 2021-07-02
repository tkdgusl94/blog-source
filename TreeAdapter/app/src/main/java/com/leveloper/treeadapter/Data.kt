package com.leveloper.treeadapter

sealed class Data(val name: String) {
    class File(name: String): Data(name)
    class Directory(name: String): Data(name)
}