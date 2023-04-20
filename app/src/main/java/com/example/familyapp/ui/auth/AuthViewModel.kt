package com.example.familyapp.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.familyapp.data.models.auth.AuthBody
import com.example.familyapp.data.models.auth.AuthResponse
import com.example.familyapp.repository.DefaultRepository
import com.example.familyapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: DefaultRepository
): ViewModel() {

    val _loginLiveData: MutableLiveData<Resource<AuthResponse>> = MutableLiveData()

    fun authWithPassword(authBody: AuthBody) = viewModelScope.launch {
        _loginLiveData.postValue(Resource.Loading())
        if (authBody.identity.isEmpty()) {
            _loginLiveData.postValue(Resource.Error("Identitas tidak boleh kosong"))
        }
        val resp = repository.authWithPassword(authBody)
        if (resp.isSuccessful) {
            resp.body()?.let {
                _loginLiveData.postValue(Resource.Success(it))
            }
        } else {
            resp.body()?.let {
                _loginLiveData.postValue(Resource.Error("Username Salah"))
            }
        }
    }


    fun isLoggedIn(): Boolean {
        return repository.isLoggedIn()
    }

    fun setValues(userName: String, familyID: String, isLoggedIn: Boolean, userID: String) {
        repository.setUserName(userName)
        repository.setFamilyID(familyID)
        repository.setLoggedIn(isLoggedIn)
        repository.setUserID(userID)
    }
}