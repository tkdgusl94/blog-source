package com.leveloper.roomconverter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.leveloper.roomconverter.R
import com.leveloper.roomconverter.data.Address
import com.leveloper.roomconverter.data.UserDao
import com.leveloper.roomconverter.data.UserEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            insertUser()
        }
    }

    private suspend fun insertUser() {
        val user = UserEntity(
            name = "leveloper",
            phones = listOf("010-1234-5678", "010-5678-1234"),
            address = Address(
                value = "서울시 강남구 어딘가",
                zipcode = 12345
            )
        )
        return withContext(Dispatchers.IO) {
            userDao.insertUser(user)
        }
    }
}