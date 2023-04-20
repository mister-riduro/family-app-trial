package com.example.familyapp.ui.family

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.familyapp.data.models.family.FamilyDetailResponse
import com.example.familyapp.data.models.role.RolesResponse
import com.example.familyapp.repository.DefaultRepository
import com.example.familyapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyFamilyViewModel @Inject constructor(
    private val repository: DefaultRepository
): ViewModel() {

    val _familyLiveData: MutableLiveData<Resource<FamilyDetailResponse>> = MutableLiveData()
    private val expandFamily = "users(family_id)"

    val _roleLiveData: MutableLiveData<Resource<RolesResponse>> = MutableLiveData()
    private val expandRole = "user_id,user_id.father_id,user_id.mother_id"

    fun getFamilyDetail(familyID: String) = viewModelScope.launch {
        _familyLiveData.postValue(Resource.Loading())
        val resp = repository.getFamilyDetail(familyID, expandFamily)
        if(resp.isSuccessful) {
            resp.body()?.let {
                _familyLiveData.postValue(Resource.Success(it))
            }
        }
    }

    fun getRolesByFamily(familyID: String) = viewModelScope.launch {
        val filterRole = "(family_id=\"$familyID\")"
        Log.d("FILTER ROLE", filterRole)
        _roleLiveData.postValue(Resource.Loading())
        val resp = repository.getUsersRoleByFamily(filterRole, expandRole)
        Log.d("ROLE RESPONSE", "$resp")
        if(resp.isSuccessful) {
            resp.body()?.let {
                _roleLiveData.postValue(Resource.Success(it))
            }
        }
    }
}