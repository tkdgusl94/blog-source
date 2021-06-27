package com.leveloper.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        viewModel.getGithubRepositories("tkdgusl94")
        viewModel.githubRepositories.observe(this) {
            it.forEach {
                println(it)
            }
        }
    }
}