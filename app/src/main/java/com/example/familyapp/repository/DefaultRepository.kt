package com.example.familyapp.repository

import com.example.familyapp.data.FamilyApi
import com.example.familyapp.data.models.ancestor.AncestorsResponse
import com.example.familyapp.data.models.auth.AuthBody
import com.example.familyapp.data.models.family.FamiliesResponse
import com.example.familyapp.data.models.family.FamilyDetailResponse
import com.example.familyapp.data.models.role.RolesResponse
import com.example.familyapp.data.models.user.UserDetailResponse
import com.example.familyapp.data.models.user.UsersResponse
import com.example.familyapp.data.preferences.Preferences
import com.example.familyapp.util.Resource
import javax.inject.Inject

class DefaultRepository @Inject constructor(private val api: FamilyApi) {
    suspend fun getUserDetail(id: String) = api.getUserDetail(id)
    suspend fun getAllUsers() = api.getAllUsers()
    suspend fun getFamilyDetail(
        id: String,
        expand: String
    ) = api.getFamilyDetail(id, expand)
    suspend fun getAllFamilies() = api.getAllFamilies()
    suspend fun getUsersRoleByFamily(
        filter: String,
        expand: String
    ) = api.getUsersRoleByFamily(filter, expand)
    suspend fun getAllAncestors(expand: String) = api.getAllAncestors(expand)
    suspend fun authWithPassword(authBody: AuthBody) = api.authWithPassword(authBody)

    fun setToken(value: String) = Preferences.instance.setToken(value)
    fun setFamilyID(value: String) = Preferences.instance.setFamilyID(value)
    fun setLoggedIn(value: Boolean) = Preferences.instance.isLoggedIn(value)
    fun setUserID(value: String) = Preferences.instance.setUserID(value)
    fun setUserName(value: String) = Preferences.instance.setUserName(value)

    fun getFamilyID() = Preferences.instance.familyID
    fun getUserID() = Preferences.instance.userID
    fun isLoggedIn() = Preferences.instance.loggedIn
    fun getUserName() = Preferences.instance.userName

}