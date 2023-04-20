package com.example.familyapp.data.models.role

import com.example.familyapp.data.models.user.UserDetailResponse
import com.google.gson.annotations.SerializedName

data class FatherMotherExpand(
    @SerializedName("father_id")
    val fatherID: UserDetailResponse,
    @SerializedName("mother_id")
    val motherID: UserDetailResponse
)
