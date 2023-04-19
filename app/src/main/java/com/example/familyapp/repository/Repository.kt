package com.example.familyapp.repository

import com.example.familyapp.data.models.ancestor.AncestorsResponse
import com.example.familyapp.data.models.family.FamiliesResponse
import com.example.familyapp.data.models.family.FamilyDetailResponse
import com.example.familyapp.data.models.role.RolesResponse
import com.example.familyapp.data.models.user.UserDetailResponse
import com.example.familyapp.data.models.user.UsersResponse
import com.example.familyapp.util.Resource

interface Repository {
    suspend fun getUserDetail(id: String): Resource<UserDetailResponse>
    suspend fun getAllUsers(): Resource<UsersResponse>
    suspend fun getFamilyDetail(id: String, expand: String): Resource<FamilyDetailResponse>
    suspend fun getAllFamilies(): Resource<FamiliesResponse>
    suspend fun getUsersRoleByFamily(filter: String, expand: String): Resource<RolesResponse>
    suspend fun getAllAncestors(expand: String): Resource<AncestorsResponse>
}