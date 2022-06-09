package com.leveloper.suspend_event

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.sign_out).setOnClickListener {
            viewModel.onClickSignOut()
        }

        viewModel.event
            .onEach { consumeEvent(this, it) }
            .launchIn(lifecycleScope)
    }

    private fun consumeEvent(context: Context, event: Event) {
        when (event) {
            is ToastEvent -> {
                Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
            is DialogEvent -> {
                AlertDialog.Builder(context)
                    .setMessage(event.message)
                    .setPositiveButton("예") { _, _ ->
                        event.tryEmit(true)
                    }
                    .setNegativeButton("아니오") { _, _ ->
                        event.tryEmit(false)
                    }
                    .show()
            }
        }
    }
}