package com.example.familyapp.data.models.ancestor


import com.google.gson.annotations.SerializedName

data class AncestorsResponse(
    @SerializedName("items")
    val ancestorsItems: List<AncestorsItem>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("perPage")
    val perPage: Int,
    @SerializedName("totalItems")
    val totalItems: Int,
    @SerializedName("totalPages")
    val totalPages: Int
)