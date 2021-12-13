package com.jmm.brsap.weathersetu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jmm.brsap.weathersetu.R
import com.jmm.brsap.weathersetu.databinding.TemplateDaysListBinding
import com.jmm.brsap.weathersetu.model.Forecastday
import com.jmm.brsap.weathersetu.utils.getWeatherIcon
import com.jmm.brsap.weathersetu.utils.myDateFormatter1
import com.jmm.brsap.weathersetu.utils.setImageWithGlide

class DaysAdapter(private val mListener: DaysAdapterInterface):
    RecyclerView.Adapter<DaysAdapter.DaysViewHolder>() {

    private val daysList = mutableListOf<Forecastday>()
//    private var currentActiveDay = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysViewHolder {
        val v = TemplateDaysListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DaysViewHolder(v,mListener)
    }

    override fun onBindViewHolder(holder: DaysViewHolder, position: Int) {
        holder.createDaysItem(daysList[position])
    }

    override fun getItemCount(): Int {
        return daysList.size
    }

    fun setDaysList(mList:List<Forecastday>){
        daysList.clear()
        daysList.addAll(mList)
        notifyDataSetChanged()
    }

    inner class DaysViewHolder(private val binding: TemplateDaysListBinding,
    private val mListener:DaysAdapterInterface):RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener{
                for (item in daysList){
                    item.isSelected = false
                }
                daysList[absoluteAdapterPosition].isSelected = true

                notifyDataSetChanged()

            }

        }

        fun createDaysItem(forecastday: Forecastday){
            binding.apply {
                tvTitle.text = myDateFormatter1(forecastday.date,"d")
                tvSubtitle.text = myDateFormatter1(forecastday.date,"EE")
                ivIcon.setImageWithGlide(getWeatherIcon(1,forecastday.day.condition.code))

                if(forecastday.isSelected){

                    bgChange.setBackgroundResource(R.drawable.bg_today_main)
                    tvTitle.setTextColor(ContextCompat.getColor(root.context, R.color.white))
                    tvSubtitle.setTextColor(ContextCompat.getColor(root.context,R.color.white_lime))

                    if(absoluteAdapterPosition<daysList.size-1){
                        mListener.onItemClick(daysList[absoluteAdapterPosition],daysList[absoluteAdapterPosition+1])
                    }else{
                        mListener.onItemClick(daysList[absoluteAdapterPosition],daysList[absoluteAdapterPosition])
                    }
                }else{

                    bgChange.setBackgroundResource(R.color.white)
                    tvTitle.setTextColor(ContextCompat.getColor(root.context, R.color.blue_700))
                    tvSubtitle.setTextColor(ContextCompat.getColor(root.context,R.color.blue_100))
                }

            }
        }
    }

    interface DaysAdapterInterface {
        fun onItemClick(forecastday: Forecastday,nextDay:Forecastday)
    }

    /*companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Forecastday>() {
            override fun areItemsTheSame(oldItem: Forecastday, newItem: Forecastday) =
                oldItem.date == newItem.date

            override fun areContentsTheSame(oldItem: Forecastday, newItem: Forecastday) =
                oldItem == newItem
        }
    }*/

}