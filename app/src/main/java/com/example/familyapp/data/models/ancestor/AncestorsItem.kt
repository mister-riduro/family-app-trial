package com.example.familyapp.data.models.ancestor


import com.google.gson.annotations.SerializedName

data class AncestorsItem(
    @SerializedName("collectionId")
    val collectionId: String,
    @SerializedName("collectionName")
    val collectionName: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("expand")
    val expand: AncestorsExpand,
    @SerializedName("id")
    val id: String,
    @SerializedName("updated")
    val updated: String,
    @SerializedName("user_id")
    val userId: String
)