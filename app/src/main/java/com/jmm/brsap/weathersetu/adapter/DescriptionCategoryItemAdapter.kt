package com.jmm.brsap.weathersetu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jmm.brsap.weathersetu.databinding.TemplateDescriptionItemListBinding
import com.jmm.brsap.weathersetu.model.ModelDescriptionCategoryItem

class DescriptionCategoryItemAdapter(private val descriptionItemList:MutableList<ModelDescriptionCategoryItem>):RecyclerView.Adapter<DescriptionCategoryItemAdapter.DescriptionCategoryItem>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DescriptionCategoryItem {
        val v = TemplateDescriptionItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return DescriptionCategoryItem(v)
    }

    override fun onBindViewHolder(holder: DescriptionCategoryItem, position: Int) {
        holder.createDescriptionCategoryItem(descriptionItemList[position])
    }

    override fun getItemCount(): Int {
        return descriptionItemList.size
    }

    inner class DescriptionCategoryItem(private val binding: TemplateDescriptionItemListBinding):RecyclerView.ViewHolder(binding.root){

        fun createDescriptionCategoryItem(descriptionItem: ModelDescriptionCategoryItem){
            binding.apply {
                tvTitle.text = descriptionItem.title
                ivIcon.setImageResource(descriptionItem.imageUrl)
            }
        }
    }
}