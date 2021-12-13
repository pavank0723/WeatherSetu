package com.jmm.brsap.weathersetu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jmm.brsap.weathersetu.databinding.TemplateDescriptionItemListBinding
import com.jmm.brsap.weathersetu.model.ModelKeyValue

class CurrentDayDetailAdapter:RecyclerView.Adapter<CurrentDayDetailAdapter.CurrentDayDetailViewHolder>() {

    private val properties = ArrayList<ModelKeyValue>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentDayDetailViewHolder {
        val v = TemplateDescriptionItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CurrentDayDetailViewHolder(v)
    }

    override fun onBindViewHolder(holder: CurrentDayDetailViewHolder, position: Int) {
        holder.createCurrentDayDetailItem(properties[position])
    }

    override fun getItemCount(): Int {
        return properties.size
    }

    public fun setProperties(newPropertiesList:ArrayList<ModelKeyValue>){
        properties.clear()
        properties.addAll(newPropertiesList)
        notifyDataSetChanged()
    }

    inner class CurrentDayDetailViewHolder(private val binding: TemplateDescriptionItemListBinding):RecyclerView.ViewHolder(binding.root){

        fun createCurrentDayDetailItem(modelKeyValue: ModelKeyValue){
            binding.apply {
                ivIcon.setImageResource(modelKeyValue.ImageUrl)
                tvProperty.text = modelKeyValue.Property
                tvTitle.text = modelKeyValue.Value
            }
        }
    }


}