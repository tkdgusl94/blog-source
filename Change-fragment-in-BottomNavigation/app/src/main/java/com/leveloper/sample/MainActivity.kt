package com.leveloper.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.leveloper.sample.databinding.ActivityMainBinding
import com.leveloper.sample.sample1.SampleOneActivity
import com.leveloper.sample.sample2.SampleTwoActivity
import com.leveloper.sample.sample3.SampleThreeActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn1.setOnClickListener {
            startActivity(Intent(this, SampleOneActivity::class.java))
        }

        binding.btn2.setOnClickListener {
            startActivity(Intent(this, SampleTwoActivity::class.java))
        }

        binding.btn3.setOnClickListener {
            startActivity(Intent(this, SampleThreeActivity::class.java))
        }
    }
}