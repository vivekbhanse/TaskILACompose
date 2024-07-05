package com.example.myassignment.domain.usecases

import android.util.Log
import com.example.myassignment.R
import com.example.myassignment.data.models.BannerContentList
import com.example.myassignment.data.models.BannerDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import java.util.Collections
import javax.inject.Inject
import kotlin.collections.ArrayList

class MainUsesCaseCompose @Inject constructor(
//    @ActivityContext context: Context
) {
    var bannerList: MutableList<BannerDetails>? = mutableListOf()
var bannerList2: ArrayList<BannerDetails> = arrayListOf()



    suspend fun intitializedBannerList(): MutableList<BannerDetails> {
        bannerList = mutableListOf()
        bannerList?.add(
            BannerDetails(
                1, R.drawable.banner1, "Cars", listOf(
                    BannerContentList("Audi", "(1909–present)", R.drawable.subbanner1),
                    BannerContentList("Maruti", "(1950–present)", R.drawable.subbanner1),
                    BannerContentList("Mercedes-Benz", "(1865–present)", R.drawable.subbanner1),
                    BannerContentList("Audi", "(1909–present)", R.drawable.subbanner1),
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
                    BannerContentList("Audi", "(1909–present)", R.drawable.subbanner1),
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
        return bannerList2
    }

    fun getBannerSublist(position: Int): MutableList<BannerDetails> {
        return mutableListOf(bannerList!!.get(position))
    }



    fun calculateStatics(position: Int): String {
        var bannerLists = listOf<BannerContentList>()
        var arr_banner= arrayListOf<String>()
        bannerLists= bannerList?.get(position)?.bannerSubList!!
        for (i in 0..bannerLists.size-1){
            arr_banner.add(bannerLists.get(i).title.toString())
        }

        var bottomSheetCharCountlist: HashMap<String, Int> = hashMapOf()
        var bottomSheetFinalCharCountlistShown: HashMap<Int, ArrayList<String>> = hashMapOf()

        bottomSheetCharCountlist.clear()
        bottomSheetFinalCharCountlistShown.clear()

        for (item in arr_banner) {
            for (i in 0..item.count() - 1) {
                if (bottomSheetCharCountlist.containsKey(item[i].toString())) {
                    var currentCount = bottomSheetCharCountlist.getValue(item[i].toString())
                    bottomSheetCharCountlist.set(item[i].toString(), currentCount + 1)
                } else {
                    bottomSheetCharCountlist.put(item[i].toString(), 1)
                }
            }
        }

        var tempArrayList: ArrayList<Int> = arrayListOf()


        bottomSheetCharCountlist.map {
            if(!tempArrayList.contains(it.value))
                tempArrayList.add(it.value)
        }


        Collections.sort(tempArrayList, Collections.reverseOrder())
        val tempArrayList2 = tempArrayList.filterIndexed { index, i -> index < 3 }


        bottomSheetCharCountlist.map {
            if (tempArrayList2.contains(it.value)) {
                if (bottomSheetFinalCharCountlistShown.containsKey(it.value)) {
                    var tempArray = bottomSheetFinalCharCountlistShown.getValue(it.value)
                    tempArray.add(it.key)
                    bottomSheetFinalCharCountlistShown.set(it.value, tempArray)
                } else {
                    bottomSheetFinalCharCountlistShown.put(it.value, arrayListOf(it.key))
                }
            }
        }


        var countList= arrayListOf<Int>()
        bottomSheetFinalCharCountlistShown.map {
            countList.add(it.key)
        }

        var countData="List "+(position+1)+ ": ".plus(arr_banner.size.toString().plus( " items")) +"\n \n"+" "+arr_banner.toString()+"\n \n"

        for (i in countList.size-1 downTo 0){
            countData=countData+"\n \n"+bottomSheetFinalCharCountlistShown.getValue(countList.get(i)).toString()+" = "+countList.get(i)
        }

        return countData
    }






}