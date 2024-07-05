package com.example.myassignment.presentation.viewmodels

import android.app.DownloadManager.Query
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myassignment.data.models.BannerContentList
import com.example.myassignment.data.models.BannerDetails
import com.example.myassignment.domain.usecases.MainUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModelCompose @Inject constructor(
    val mainUsesCase: MainUsesCase
) : ViewModel() {

    val mBannerPostLiveData = MutableLiveData<MutableList<BannerDetails>>()
    var mBannerPostLiveData2 = MutableStateFlow<List<BannerDetails>>(emptyList())
    val mBannerPostBannerSublist = MutableStateFlow<List<BannerContentList>>(emptyList())
    var finalData = MutableStateFlow<String>("")


    init {
        var job = viewModelScope.launch(Dispatchers.Default) {
            val listBanner = mainUsesCase.intitializedBannerList()
            mBannerPostLiveData.postValue(listBanner)

        }
        var job2 = viewModelScope.launch(Dispatchers.Default) {
            val listBanner = mainUsesCase.intitializedBannerList2()
            mBannerPostLiveData2.update { listBanner }

        }
        runBlocking {
            job.join()
            job2.join()
        }

        operationsOfData(position = 0)
    }

    fun operationsOfData(position: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val final = mainUsesCase.calculateStatics(position)
            finalData.update { final }
        }
    }

    fun getSublistByIndex(position: Int) = viewModelScope.launch(Dispatchers.Default) {
        val dataList = mainUsesCase.getBannerSublist(position)
        mBannerPostBannerSublist.update { dataList }
    }



}