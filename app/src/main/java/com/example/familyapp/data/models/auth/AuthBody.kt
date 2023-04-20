package com.example.familyapp.data.models.auth

import com.google.gson.annotations.SerializedName

data class AuthBody(
    @SerializedName("identity")
    val identity: String,
    @SerializedName("password")
    val password: String
)
