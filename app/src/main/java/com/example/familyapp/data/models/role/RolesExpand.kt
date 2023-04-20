package com.example.familyapp.data.models.role


import com.example.familyapp.data.models.user.UserDetailResponse
import com.google.gson.annotations.SerializedName

data class RolesExpand(
    @SerializedName("user_id")
    val userId: RolesUserDetailResponse
)