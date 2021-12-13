package com.jmm.brsap.weathersetu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jmm.brsap.weathersetu.databinding.TemplateTodayTimeListBinding
import com.jmm.brsap.weathersetu.model.ModelTodayCategoryItem

class TodayCategoryItemAdapter(private val todayCategoryItemList:MutableList<ModelTodayCategoryItem>):RecyclerView.Adapter<TodayCategoryItemAdapter.TodayCategoryItem>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodayCategoryItem {
        val v = TemplateTodayTimeListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TodayCategoryItem(v)
    }

    override fun onBindViewHolder(holder: TodayCategoryItem, position: Int) {
        holder.createTodayCategoryItem(todayCategoryItemList[position])
    }

    override fun getItemCount(): Int {
        return todayCategoryItemList.size
    }

    inner class TodayCategoryItem(private val binding: TemplateTodayTimeListBinding):RecyclerView.ViewHolder(binding.root){

        fun createTodayCategoryItem(todayCategoryItem:ModelTodayCategoryItem){
            binding.apply {
                tvTitle.text = todayCategoryItem.title
                tvSubtitle.text = todayCategoryItem.subTitle
                ivIcon.setImageResource(todayCategoryItem.imageUrl)
            }
        }
    }
}