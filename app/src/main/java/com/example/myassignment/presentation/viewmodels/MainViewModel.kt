package com.example.myassignment.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myassignment.data.models.BannerDetails
import com.example.myassignment.domain.usecases.MainUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val mainUsesCase: MainUsesCase
) : ViewModel() {

    val mBannerPostLiveData = MutableLiveData<MutableList<BannerDetails>>()
    var finalData = MutableLiveData<String>("initial value")
   init{
        var job=viewModelScope.launch(Dispatchers.IO) {
            val listBanner = mainUsesCase.intitializedBannerList()
            mBannerPostLiveData.postValue(listBanner)

        }
       runBlocking {
           job.join()
       }

       operationsOfData(position=0)
    }

    fun operationsOfData(position: Int) {
        viewModelScope.launch(Dispatchers.IO) {
             val final= mainUsesCase.calculateStatics(position)
            finalData.postValue(final)
        }
    }
}