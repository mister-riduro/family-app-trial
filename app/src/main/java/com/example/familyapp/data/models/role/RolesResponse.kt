package com.example.familyapp.data.models.role


import com.google.gson.annotations.SerializedName

data class RolesResponse(
    @SerializedName("items")
    val rolesItems: List<RolesItem>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("perPage")
    val perPage: Int,
    @SerializedName("totalItems")
    val totalItems: Int,
    @SerializedName("totalPages")
    val totalPages: Int
)