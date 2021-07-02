package com.leveloper.treeadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val nodes = makeSample()

        recyclerView.adapter = FileAdapter(nodes)
    }

    /**
     * sample
     *
     * dir1
     *     ㄴ dir2
     *     ㄴ file1
     * dir3
     *     ㄴ dir4
     *         ㄴ file2
     *     ㄴ dir5
     *         ㄴ file3
     *         ㄴ file4
     */
    private fun makeSample(): List<Node<Data>> {
        val dir1 = Node<Data>(Data.Directory("dir1"))
            .addChild(Node(Data.Directory("dir2")))
            .addChild(Node(Data.File("file1")))

        val dir4 = Node<Data>(Data.Directory("dir4"))
            .addChild(Node(Data.File("file2")))

        val dir5 = Node<Data>(Data.Directory("dir5"))
            .addChild(Node(Data.File("file3")))
            .addChild(Node(Data.File("file4")))

        val dir3 = Node<Data>(Data.Directory("dir3"))
            .addChild(dir4)
            .addChild(dir5)

        return listOf(dir1, dir3)
    }
}