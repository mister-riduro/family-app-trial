package com.example.familyapp.repository

import com.example.familyapp.data.FamilyApi
import com.example.familyapp.data.models.ancestor.AncestorsResponse
import com.example.familyapp.data.models.family.FamiliesResponse
import com.example.familyapp.data.models.family.FamilyDetailResponse
import com.example.familyapp.data.models.role.RolesResponse
import com.example.familyapp.data.models.user.UserDetailResponse
import com.example.familyapp.data.models.user.UsersResponse
import com.example.familyapp.util.Resource
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val api: FamilyApi
): Repository {
    override suspend fun getUserDetail(id: String): Resource<UserDetailResponse> {
        return try {
            val response = api.getUserDetail(id)
            val result = response.body()

            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error occured")
        }
    }

    override suspend fun getAllUsers(): Resource<UsersResponse> {
        return try {
            val response = api.getAllUsers()
            val result = response.body()

            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error occured")
        }
    }

    override suspend fun getFamilyDetail(
        id: String,
        expand: String
    ): Resource<FamilyDetailResponse> {
        return try {
            val response = api.getFamilyDetail(id, expand)
            val result = response.body()

            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error occured")
        }
    }

    override suspend fun getAllFamilies(): Resource<FamiliesResponse> {
        return try {
            val response = api.getAllFamilies()
            val result = response.body()

            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error occured")
        }
    }

    override suspend fun getUsersRoleByFamily(
        filter: String,
        expand: String
    ): Resource<RolesResponse> {
        return try {
            val response = api.getUsersRoleByFamily(filter, expand)
            val result = response.body()

            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error occured")
        }
    }

    override suspend fun getAllAncestors(expand: String): Resource<AncestorsResponse> {
        return try {
            val response = api.getAllAncestors(expand)
            val result = response.body()

            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error occured")
        }
    }


}