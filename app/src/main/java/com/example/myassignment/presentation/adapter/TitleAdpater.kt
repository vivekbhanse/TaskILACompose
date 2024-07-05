package com.example.myassignment.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myassignment.R
import com.example.myassignment.data.models.BannerContentList
import com.example.myassignment.data.models.BannerDetails
import com.example.myassignment.databinding.ItemListBannerconttentBinding


class TitleAdpater(var dataList: List<BannerContentList>, val parentPos: Int) :
    RecyclerView.Adapter<TitleAdpater.ViewHolder>() {
    var courseList: List<BannerContentList>?=null
    class ViewHolder(val binding: ItemListBannerconttentBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemListBannerconttentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }
    fun filterList(filterlist: ArrayList<BannerContentList>) {
        dataList = filterlist
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            dataList.get(position).subBannerImage?.let { binding.recImageView.setImageResource(it) }
            binding.txtTitle.text = dataList.get(position).title
            binding.txtSubTitle.text = dataList.get(position).subTitle

        }


    }
}
