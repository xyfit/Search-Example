package com.example.searchexample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.searchexample.databinding.ActivityMainBinding
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btn1.setOnClickListener {
            startAnyActivity(NativeActionBarSearchActivity::class)
        }

        binding.btn2.setOnClickListener {
            startAnyActivity(SearchViewActivity::class)
        }
        binding.btn3.setOnClickListener {
            startAnyActivity(CustomToolbarSearchActivity::class)
        }

    }

    private fun startAnyActivity(activity: KClass<out Activity>) {
        startActivity(Intent(this, activity.java))
    }

}