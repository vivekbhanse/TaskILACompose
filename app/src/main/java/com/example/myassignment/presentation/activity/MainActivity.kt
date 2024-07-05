package com.example.myassignment.presentation.activity

import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.myassignment.R
import com.example.myassignment.data.models.BannerContentList
import com.example.myassignment.data.models.BannerDetails
import com.example.myassignment.databinding.ActivityMainBinding
import com.example.myassignment.presentation.adapter.TitleAdpater
import com.example.myassignment.presentation.adapter.ViewPagerAdapter
import com.example.myassignment.presentation.viewmodels.MainViewModel
import com.example.myassignment.presentation.fragments.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var mViewModel: MainViewModel
    var dataList: MutableList<BannerDetails> = mutableListOf()
    lateinit var adapter: TitleAdpater
    var positionIndex = 0;
    var result=""
    private var ivArrayDotsPager = emptyArray<ImageView>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mViewModel.mBannerPostLiveData.observe(this, Observer {
            dataList = it
            binding.recList.layoutManager = LinearLayoutManager(this@MainActivity)
            binding.viewPagerMain.adapter = ViewPagerAdapter(this@MainActivity, it)
            initView()
            initRecylerView(it)
        })

//        mViewModel.finalData.observe(this, Observer {
//                result=it
//        })

    }


    private fun initView() {
        binding.run {

                moreFab.setOnClickListener {
                    val modal = BottomSheetDialog(result)
                    supportFragmentManager.let { modal.show(it, BottomSheetDialog.TAG) }

                }

            setupPagerIndicatorDots()
            ivArrayDotsPager[0].setImageResource(R.drawable.selected_dot);
            viewPagerMain.addOnPageChangeListener(object : OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {

                }

                override fun onPageSelected(position: Int) {
                    for (i in ivArrayDotsPager.indices) {
                        ivArrayDotsPager[i].setImageResource(R.drawable.unselected_dot)
                    }
                    ivArrayDotsPager[position].setImageResource(R.drawable.selected_dot)
                    positionIndex = position
                    initRecylerView(dataList, position)
                    mViewModel.operationsOfData(position)

                }

                override fun onPageScrollStateChanged(state: Int) {

                }
            })

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    if (newText != null) {
                        filter(newText, positionIndex)
                    }
                    return false
                }

            })
        }


    }

    private fun initRecylerView(bannerDetails: MutableList<BannerDetails>, postion: Int = 0) {
        binding.run {
            adapter = TitleAdpater(bannerDetails.get(postion).bannerSubList!!.toList(), postion)
            recList.adapter = adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupPagerIndicatorDots() {
        ivArrayDotsPager = Array(dataList.size) {
            ImageView(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).also { it.setMargins(10, 0, 10, 0) }
                setImageResource(R.drawable.unselected_dot)
                setOnClickListener { it.alpha = 1f }
            }
        }
        binding.layPagerDots.apply {
            ivArrayDotsPager.forEach(::addView)
            bringToFront()
        }
    }

    private fun filter(text: String, postions: Int) {
        val filteredlist: ArrayList<BannerContentList> = ArrayList()

        for (item in dataList.get(postions).bannerSubList!!) {
            if (item.title!!.toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
            adapter.filterList(arrayListOf())
        } else {
            adapter.filterList(filteredlist)
        }
    }



}