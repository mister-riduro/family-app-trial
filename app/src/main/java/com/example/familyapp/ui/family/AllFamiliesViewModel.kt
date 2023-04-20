package com.example.familyapp.ui.family

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.familyapp.data.models.family.FamiliesResponse
import com.example.familyapp.repository.DefaultRepository
import com.example.familyapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllFamiliesViewModel @Inject constructor(
    private val repository: DefaultRepository
):ViewModel() {
    val _allFamiliesLiveData: MutableLiveData<Resource<FamiliesResponse>> = MutableLiveData()

    fun getAllFamilies() = viewModelScope.launch {
        _allFamiliesLiveData.postValue(Resource.Loading())
        val resp = repository.getAllFamilies()
        if(resp.isSuccessful) {
            resp.body()?.let {
                _allFamiliesLiveData.postValue(Resource.Success(it))
            }
        }
    }

}