package com.example.familyapp.data.models.family


import com.google.gson.annotations.SerializedName

data class FamiliesResponse(
    @SerializedName("items")
    val familiesItems: List<FamiliesItem>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("perPage")
    val perPage: Int,
    @SerializedName("totalItems")
    val totalItems: Int,
    @SerializedName("totalPages")
    val totalPages: Int
)