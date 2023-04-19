package com.example.familyapp.data.models.family


import com.example.familyapp.data.models.user.UserDetailResponse
import com.google.gson.annotations.SerializedName

data class FamilyDetailExpand(
    @SerializedName("users(family_id)")
    val usersFamilyID: List<UserDetailResponse>
)