package com.example.familyapp.data

import com.example.familyapp.data.models.auth.AuthBody
import com.example.familyapp.data.models.auth.AuthResponse
import com.example.familyapp.data.models.ancestor.AncestorsResponse
import com.example.familyapp.data.models.family.FamiliesResponse
import com.example.familyapp.data.models.family.FamilyDetailResponse
import com.example.familyapp.data.models.role.RolesResponse
import com.example.familyapp.data.models.user.UserDetailResponse
import com.example.familyapp.data.models.user.UsersResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface FamilyApi {
    @GET("/api/collections/users/records/{id}")
    suspend fun getUserDetail(
        @Path("id") userID: String
    ): Response<UserDetailResponse>

    @GET("/api/collections/users/records")
    suspend fun getAllUsers(): Response<UsersResponse>

    @GET("/api/collections/families/records/{id}")
    suspend fun getFamilyDetail(
        @Path("id") familyID: String,
        @Query("expand") expand: String
    ): Response<FamilyDetailResponse>

    @GET("/api/collections/families/records")
    suspend fun getAllFamilies(): Response<FamiliesResponse>

    @GET("/api/collections/roles/records")
    suspend fun getUsersRoleByFamily(
        @Query("filter") filter: String,
        @Query("expand") expand: String
    ): Response<RolesResponse>

    @GET("/api/collections/ancestors/records")
    suspend fun getAllAncestors(
        @Query("expand") expand: String
    ): Response<AncestorsResponse>

    @POST("/api/collections/users/auth-with-password")
    suspend fun authWithPassword(
        @Body authBody: AuthBody
    ): Response<AuthResponse>
}