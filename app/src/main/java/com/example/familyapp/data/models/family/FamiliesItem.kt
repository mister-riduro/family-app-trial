package com.example.familyapp.data.models.family


import com.google.gson.annotations.SerializedName

data class FamiliesItem(
    @SerializedName("collectionId")
    val collectionId: String,
    @SerializedName("collectionName")
    val collectionName: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("domicile")
    val domicile: String,
    @SerializedName("family_name")
    val familyName: String,
    @SerializedName("family_photo")
    val familyPhoto: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("updated")
    val updated: String
)