package com.example.familyapp.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.familyapp.MainActivity
import com.example.familyapp.data.models.auth.AuthBody
import com.example.familyapp.databinding.ActivityAuthBinding
import com.example.familyapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private val password = "password"
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        checkSession()

        binding.btnVisitEventWeb.setOnClickListener {
            val authBody = AuthBody(binding.etIdentity.text.toString(), password)

            authViewModel.authWithPassword(authBody)
            authViewModel._loginLiveData.observe(this) { response ->
                when(response) {
                    is Resource.Error -> {
                        Toast.makeText(this, "${response.message}", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        authViewModel.setValues(response.data?.record?.name.toString(), response.data?.record?.familyId.toString(), true, response.data?.record?.id.toString())
                        navigateToHome()
                    }
                }
            }
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun checkSession() {
        if (authViewModel.isLoggedIn()) {
            navigateToHome()
        }
    }
}