package com.jmm.brsap.weathersetu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jmm.brsap.weathersetu.databinding.TemplateDaysListBinding
import com.jmm.brsap.weathersetu.model.ModelDaysCategoryItem

class DaysCategoryItemAdapter(private val daysCategoryItemList:MutableList<ModelDaysCategoryItem>):RecyclerView.Adapter<DaysCategoryItemAdapter.DaysCategoryItem>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DaysCategoryItemAdapter.DaysCategoryItem {
        val v = TemplateDaysListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DaysCategoryItem(v)
    }

    override fun onBindViewHolder(holder: DaysCategoryItemAdapter.DaysCategoryItem, position: Int) {
        holder.createDaysCategoryItem(daysCategoryItemList[position])
    }

    override fun getItemCount(): Int {
        return daysCategoryItemList.size
    }

    inner class DaysCategoryItem(private val binding: TemplateDaysListBinding):RecyclerView.ViewHolder(binding.root){

        fun createDaysCategoryItem(daysCategoryItem:ModelDaysCategoryItem){
            binding.apply {
                tvTitle.text = daysCategoryItem.title
                tvSubtitle.text = daysCategoryItem.subTitle
                ivIcon.setImageResource(daysCategoryItem.imageUrl)
            }
        }
    }


}