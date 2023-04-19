package com.example.familyapp.data.models.ancestor


import com.example.familyapp.data.models.user.UserDetailResponse
import com.google.gson.annotations.SerializedName

data class AncestorsExpand(
    @SerializedName("user_id")
    val userId: UserDetailResponse
)