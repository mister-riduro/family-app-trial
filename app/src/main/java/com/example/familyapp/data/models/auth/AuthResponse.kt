package com.example.familyapp.data.models.auth

import com.example.familyapp.data.models.user.UserDetailResponse
import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("record")
    val record: UserDetailResponse,
    @SerializedName("token")
    val token: String
)