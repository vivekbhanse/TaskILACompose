package com.example.myassignment.domain.usecases

import com.example.myassignment.R
import com.example.myassignment.data.models.BannerContentList
import com.example.myassignment.data.models.BannerDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Collections
import javax.inject.Inject

class MainUsesCase @Inject constructor(
//    @ActivityContext context: Context
) {
    var bannerList: MutableList<BannerDetails>? = mutableListOf()
    var bannerList2: ArrayList<BannerDetails> = arrayListOf()

    //first state whether the search is happening or not
    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    //second state the text typed by the user
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    suspend fun intitializedBannerList(): MutableList<BannerDetails> {
        bannerList = mutableListOf()
        bannerList?.add(
            BannerDetails(
                1, R.drawable.banner1, "Cars", listOf(
                    BannerContentList("Audi", "(1909–present)", R.drawable.subbanner1),
                    BannerContentList("Maruti", "(1950–present)", R.drawable.subbanner1),
                    BannerContentList("Mercedes-Benz", "(1865–present)", R.drawable.subbanner1),
                    BannerContentList("BMW", "(1909–present)", R.drawable.subbanner1),
                    BannerContentList("Nissan", "(1909–present)", R.drawable.subbanner1),
                    BannerContentList("Mono", "(1909–present)", R.drawable.subbanner1),
                    BannerContentList("Ferrari", "(1909–present)", R.drawable.subbanner1),
                    BannerContentList("Mustang", "(1909–present)", R.drawable.subbanner1),
                    BannerContentList("Suziki", "(1909–present)", R.drawable.subbanner1),
                )
            )
        )
        bannerList?.add(
            BannerDetails(
                2, R.drawable.banner1, "Animal", listOf(
                    BannerContentList("Tigor", "Wild", R.drawable.subbanner2),
                    BannerContentList("Lion", "Wild", R.drawable.subbanner2),
                    BannerContentList("Dog", "Pet", R.drawable.subbanner2),
                    BannerContentList("Dog1", "Pet", R.drawable.subbanner2),
                    BannerContentList("Dog2", "Pet", R.drawable.subbanner2),
                )
            )
        )
        bannerList?.add(
            BannerDetails(
                3, R.drawable.banner2, "Other", listOf(
                    BannerContentList("Dolphine", "subtitle 1", R.drawable.subbanner3),
                    BannerContentList("Whale", "subtitle 1", R.drawable.subbanner3),
                    BannerContentList("Shark", "subtitle 1", R.drawable.subbanner3),
                    BannerContentList("Shark1", "subtitle 1", R.drawable.subbanner3),
                    BannerContentList("Shark2", "subtitle 1", R.drawable.subbanner3),
                )
            )
        )
        bannerList?.add(
            BannerDetails(
                4, R.drawable.banner1, "Bird", listOf(
                    BannerContentList("Swan", "subtitle 1", R.drawable.subbanner4),
                    BannerContentList("Parrot", "subtitle 1", R.drawable.subbanner4),
                    BannerContentList("Peacock", "subtitle 1", R.drawable.subbanner4),
                    BannerContentList("Peacock1", "subtitle 1", R.drawable.subbanner4),
                    BannerContentList("Peacock2", "subtitle 1", R.drawable.subbanner4),
                )
            )
        )
        return bannerList!!
    }


    suspend fun intitializedBannerList2(): List<BannerDetails> {
        bannerList2 = arrayListOf()
        bannerList2.add(
            BannerDetails(
                1, R.drawable.banner1, "Cars", listOf(
                    BannerContentList("Audi", "(1909–present)", R.drawable.subbanner1),
                    BannerContentList("Maruti", "(1950–present)", R.drawable.subbanner1),
                    BannerContentList("Mercedes-Benz", "(1865–present)", R.drawable.subbanner1),
                    BannerContentList("BMW", "(1909–present)", R.drawable.subbanner1),
                    BannerContentList("Nissan", "(1909–present)", R.drawable.subbanner1),
                    BannerContentList("Mono", "(1909–present)", R.drawable.subbanner1),
                    BannerContentList("Ferrari", "(1909–present)", R.drawable.subbanner1),
                    BannerContentList("Mustang", "(1909–present)", R.drawable.subbanner1),
                    BannerContentList("Suziki", "(1909–present)", R.drawable.subbanner1),
                )
            )
        )
        bannerList2.add(
            BannerDetails(
                2, R.drawable.banner1, "Animal", listOf(
                    BannerContentList("Tigor", "Wild", R.drawable.subbanner2),
                    BannerContentList("Lion", "Wild", R.drawable.subbanner2),
                    BannerContentList("Dog", "Pet", R.drawable.subbanner2),
                    BannerContentList("Dog1", "Pet", R.drawable.subbanner2),
                    BannerContentList("Dog2", "Pet", R.drawable.subbanner2),
                )
            )
        )
        bannerList2.add(
            BannerDetails(
                3, R.drawable.banner2, "Other", listOf(
                    BannerContentList("Dolphine", "subtitle 1", R.drawable.subbanner3),
                    BannerContentList("Whale", "subtitle 1", R.drawable.subbanner3),
                    BannerContentList("Shark", "subtitle 1", R.drawable.subbanner3),
                    BannerContentList("Shark1", "subtitle 1", R.drawable.subbanner3),
                    BannerContentList("Shark2", "subtitle 1", R.drawable.subbanner3),
                )
            )
        )
        bannerList2.add(
            BannerDetails(
                4, R.drawable.banner1, "Bird", listOf(
                    BannerContentList("Swan", "subtitle 1", R.drawable.subbanner4),
                    BannerContentList("Parrot", "subtitle 1", R.drawable.subbanner4),
                    BannerContentList("Peacock", "subtitle 1", R.drawable.subbanner4),
                    BannerContentList("Peacock1", "subtitle 1", R.drawable.subbanner4),
                    BannerContentList("Peacock2", "subtitle 1", R.drawable.subbanner4),
                )
            )
        )
        return bannerList2
    }

    fun getBannerSublist(position: Int): List<BannerContentList> {
        return bannerList!!.get(position).bannerSubList!!
    }


    fun calculateStatics(position: Int): String {
        var bannerLists = listOf<BannerContentList>()
        var arr_banner= arrayListOf<String>()
        bannerLists= bannerList?.get(position)?.bannerSubList!!
        for (i in 0..bannerLists.size-1){
            arr_banner.add(bannerLists.get(i).title.toString())
        }

        var listOfCount: HashMap<String, Int> = hashMapOf()
        var ultimateList: HashMap<Int, java.util.ArrayList<String>> = hashMapOf()

        listOfCount.clear()
        ultimateList.clear()

        for (item in arr_banner) {
            for (i in 0..item.count() - 1) {
                if (listOfCount.containsKey(item[i].toString())) {
                    var latestCount = listOfCount.getValue(item[i].toString())
                    listOfCount.set(item[i].toString(), latestCount + 1)
                } else {
                    listOfCount.put(item[i].toString(), 1)
                }
            }
        }

        var tempo_Arr: java.util.ArrayList<Int> = arrayListOf()


        listOfCount.map {
            if(!tempo_Arr.contains(it.value))
                tempo_Arr.add(it.value)
        }


        Collections.sort(tempo_Arr, Collections.reverseOrder())
        val tempArrayList2 = tempo_Arr.filterIndexed { index, i -> index < 3 }


        listOfCount.map {
            if (tempArrayList2.contains(it.value)) {
                if (ultimateList.containsKey(it.value)) {
                    var tempArray = ultimateList.getValue(it.value)
                    tempArray.add(it.key)
                    ultimateList.set(it.value, tempArray)
                } else {
                    ultimateList.put(it.value, arrayListOf(it.key))
                }
            }
        }


        var countList= arrayListOf<Int>()
        ultimateList.map {
            countList.add(it.key)
        }

        var countData="List "+(position+1)+ ": ".plus(arr_banner.size.toString().plus( " items")) +"\n \n"+" "+arr_banner.toString()+"\n \n"

        for (i in countList.size-1 downTo 0){
            countData=countData+"\n \n"+ultimateList.getValue(countList.get(i)).toString()+" = "+countList.get(i)
        }

        return countData
    }


}