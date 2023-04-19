package com.example.familyapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.familyapp.databinding.ActivityDetailFamilyBinding

class DetailFamilyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailFamilyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFamilyBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}