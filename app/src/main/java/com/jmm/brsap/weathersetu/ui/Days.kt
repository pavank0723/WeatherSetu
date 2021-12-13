package com.jmm.brsap.weathersetu.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pocketmoney.utils.Status
import com.jmm.brsap.weathersetu.R
import com.jmm.brsap.weathersetu.adapter.CurrentDayDetailAdapter
import com.jmm.brsap.weathersetu.adapter.DaysAdapter
import com.jmm.brsap.weathersetu.databinding.ActivityDaysBinding
import com.jmm.brsap.weathersetu.model.Forecastday
import com.jmm.brsap.weathersetu.model.ModelKeyValue
import com.jmm.brsap.weathersetu.utils.*
import com.jmm.brsap.weathersetu.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class Days : AppCompatActivity(), DaysAdapter.DaysAdapterInterface {

    private lateinit var binding:ActivityDaysBinding

    private lateinit var daysAdapter: DaysAdapter
    private lateinit var currentDayDetailAdapter: CurrentDayDetailAdapter


    private val viewModel by viewModels<WeatherViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDaysBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subscribeObservers()
        setupRVDays()
        setupRVDescription()

        viewModel.getForecastWeather("Mumbai",7)
        binding.ivBack.setOnClickListener {
            finish()
        }


    }

    private fun subscribeObservers(){

        viewModel.forecastWeather.observe(this, { _result ->
            when (_result.status) {
                Status.SUCCESS -> {
                    _result._data?.let {
                        it.forecast.forecastday[0].isSelected = true
                        daysAdapter.setDaysList(it.forecast.forecastday)
                    }
                    displayLoading(false)
                }
                Status.LOADING -> {
                    displayLoading(true)
                }
                Status.ERROR -> {
                    displayLoading(false)
                    _result.message?.let {
                        displayError(it)
                    }
                }
            }
        })
    }

    private fun setupRVDays(){
        daysAdapter = DaysAdapter(this)
        binding.rvDays.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            adapter = daysAdapter
        }
    }
    private fun setupRVDescription(){
        currentDayDetailAdapter = CurrentDayDetailAdapter()
        binding.layoutDetailOfActiveDate.layoutCurrentDay.rvDetails.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            adapter = currentDayDetailAdapter
        }
    }


    private fun generateProperties(forecastday: Forecastday):ArrayList<ModelKeyValue>{
        var propertiesList =ArrayList<ModelKeyValue>()

        propertiesList.add(ModelKeyValue("Humidity",forecastday.day.avghumidity.toString()+"%",R.drawable.sun_6))
        propertiesList.add(ModelKeyValue("Wind",forecastday.day.maxwind_kph.toString()+"km/h",R.drawable.moon_1))
        propertiesList.add(ModelKeyValue("Visibility",forecastday.day.avgvis_km.toString()+"km",R.drawable.cloud_122))
        propertiesList.add(ModelKeyValue("UV",forecastday.day.uv.toString(),R.drawable.sun_26))
        propertiesList.add(ModelKeyValue("Precip",forecastday.day.totalprecip_mm.toString()+"mm",R.drawable.cloud_302))
        propertiesList.add(ModelKeyValue("Rain",forecastday.day.maxwind_kph.toString()+"%",R.drawable.rain))
        propertiesList.add(ModelKeyValue("Snow",forecastday.day.maxwind_kph.toString()+"%",R.drawable.snow))

        return propertiesList
    }


    private fun setCurrentDayView(forecastday: Forecastday){
        currentDayDetailAdapter.setProperties(generateProperties(forecastday))

        binding.layoutDetailOfActiveDate.layoutCurrentDay.apply {
            tvTempCelsius.setTemperature1(forecastday.day.avgtemp_c)
            ivIcon.setImageWithGlide(getWeatherIcon(1,forecastday.day.condition.code))
            val feelslike = "Feels like ${forecastday.day.maxtemp_c}"
            tvFeelsLike.setTemperature1(feelslike)
            tvDescription.text = forecastday.day.condition.text

            val minMax = "${forecastday.day.mintemp_c}°/ ${forecastday.day.maxtemp_c}"
            tvTempMinMax.setTemperature1(minMax)
        }

    }
    private fun setNextDayView(forecastday: Forecastday){
        binding.layoutDetailOfActiveDate.layoutNextDay.apply {
            ivWeatherIcon.setImageWithGlide(getWeatherIcon(1,forecastday.day.condition.code))
            tvWeatherTitle.text = forecastday.day.condition.text
            tvDate.text = myDateFormatter1(forecastday.date,"EE,dd MMMM")
            val minMax = "${forecastday.day.mintemp_c}°/${forecastday.day.maxtemp_c}"
            tvTempMinOrMax.setTemperature(minMax)
        }
    }

    private fun displayLoading(status: Boolean){
        binding.progressBar.isVisible = status
    }

    private fun displayError(it:String){
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()

    }

    override fun onItemClick(forecastday: Forecastday, nextDay: Forecastday) {
        setCurrentDayView(forecastday)
        setNextDayView(nextDay)
    }


}