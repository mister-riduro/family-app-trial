package com.example.familyapp.data.models.user
import com.google.gson.annotations.SerializedName


data class UserDetailResponse(
    @SerializedName("additional_desc")
    val additionalDesc: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("born_date")
    val bornDate: String,
    @SerializedName("child_id")
    val childId: List<String>,
    @SerializedName("collectionId")
    val collectionId: String,
    @SerializedName("collectionName")
    val collectionName: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("emailVisibility")
    val emailVisibility: Boolean,
    @SerializedName("family_id")
    val familyId: String,
    @SerializedName("father_id")
    val fatherId: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("mother_id")
    val motherId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("updated")
    val updated: String,
    @SerializedName("user_type")
    val userType: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("verified")
    val verified: Boolean
)