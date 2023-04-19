package com.example.familyapp.data.models.role


import com.google.gson.annotations.SerializedName

data class RolesItem(
    @SerializedName("collectionId")
    val collectionId: String,
    @SerializedName("collectionName")
    val collectionName: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("expand")
    val rolesExpand: RolesExpand,
    @SerializedName("family_id")
    val familyId: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("updated")
    val updated: String,
    @SerializedName("user_id")
    val userId: String
)