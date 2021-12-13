package com.jmm.brsap.weathersetu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition
import com.jmm.brsap.weathersetu.R
import com.jmm.brsap.weathersetu.databinding.TemplateTodayTimeListBinding
import com.jmm.brsap.weathersetu.model.Hour
import com.jmm.brsap.weathersetu.utils.getWeatherIcon
import com.jmm.brsap.weathersetu.utils.myTimeFormatter
import com.jmm.brsap.weathersetu.utils.setImageWithGlide
import com.jmm.brsap.weathersetu.utils.setTemperature

class HourlyTempAdapter:RecyclerView.Adapter<HourlyTempAdapter.HourlyTempViewHolder>(){

    private val hourlyTemperature = ArrayList<Hour>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyTempViewHolder {
        val v = TemplateTodayTimeListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HourlyTempViewHolder(v)
    }

    override fun onBindViewHolder(holder: HourlyTempViewHolder, position: Int) {
        holder.createHourlyTempItem(hourlyTemperature[position])
    }

    override fun getItemCount(): Int {
        return hourlyTemperature.size
    }

    fun setData(newList: List<Hour>){
        val diffCallback = HourlyDiffCallback(hourlyTemperature, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        hourlyTemperature.clear()
        hourlyTemperature.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class HourlyTempViewHolder(private val binding:TemplateTodayTimeListBinding):RecyclerView.ViewHolder(binding.root){

        fun createHourlyTempItem(hour: Hour){
            binding.apply {
                tvTitle.text = myTimeFormatter(hour.time)
                ivIcon.setImageWithGlide(getWeatherIcon(hour.is_day,hour.condition.code))

                tvSubtitle.setTemperature(hour.temp_c)
            }
        }
    }

    inner class HourlyDiffCallback(private val oldList: List<Hour>, private val newList: List<Hour>) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].time === newList[newItemPosition].time
        }

        override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            val (_, value, name) = oldList[oldPosition]
            val (_, value1, name1) = newList[newPosition]

            return name == name1 && value == value1
        }

        @Nullable
        override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
            return super.getChangePayload(oldPosition, newPosition)
        }
    }
}