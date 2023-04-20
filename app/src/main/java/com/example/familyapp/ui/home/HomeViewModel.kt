package com.example.familyapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.familyapp.data.models.ancestor.AncestorsResponse
import com.example.familyapp.repository.DefaultRepository
import com.example.familyapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DefaultRepository
): ViewModel() {

    val _ancestorsLiveData: MutableLiveData<Resource<AncestorsResponse>> = MutableLiveData()
    private val expand = "user_id"

    fun getAncestorsData() = viewModelScope.launch {
        _ancestorsLiveData.postValue(Resource.Loading())
        val resp = repository.getAllAncestors(expand)
        if(resp.isSuccessful) {
            resp.body()?.let {
                _ancestorsLiveData.postValue(Resource.Success(it))
            }
        }
    }
}