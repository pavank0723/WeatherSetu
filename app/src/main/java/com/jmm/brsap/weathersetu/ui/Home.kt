package com.jmm.brsap.weathersetu.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pocketmoney.utils.Status
import com.example.weatherapplication.models.Current
import com.jmm.brsap.weathersetu.R
import com.jmm.brsap.weathersetu.adapter.CurrentDayDetailAdapter
import com.jmm.brsap.weathersetu.adapter.HourlyTempAdapter
import com.jmm.brsap.weathersetu.databinding.FragmentHomeBinding
import com.jmm.brsap.weathersetu.model.*
import com.jmm.brsap.weathersetu.utils.getWeatherIcon
import com.jmm.brsap.weathersetu.utils.myDateFormatter
import com.jmm.brsap.weathersetu.utils.setTemperature
import com.jmm.brsap.weathersetu.utils.setTemperature2
import com.jmm.brsap.weathersetu.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class Home : Fragment(){

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var currentDayDetailAdapter: CurrentDayDetailAdapter
    private lateinit var hourlyTempAdapter: HourlyTempAdapter

    private val viewModel by viewModels<WeatherViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        setupRVDescription()
        setupRVHours()
        viewModel.getCurrentWeather("mumbai")
        viewModel.getForecastWeather("Mumbai",1)

    }

    private fun subscribeObservers(){

        viewModel.currentWeather.observe(viewLifecycleOwner, { _result ->
            when (_result.status) {
                Status.SUCCESS -> {
                    _result._data?.let {
                        setCurrentWeather(it)
                        currentDayDetailAdapter.setProperties(generatePropertiesByCurrentWeather(it.current))
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

        viewModel.forecastWeather.observe(viewLifecycleOwner, { _result ->
            when (_result.status) {
                Status.SUCCESS -> {
                    _result._data?.let {
                        hourlyTempAdapter.setData(it.forecast.forecastday[0].hour)
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

//        PENDING
        viewModel.searchWeather.observe(viewLifecycleOwner, { _result ->
            when (_result.status) {
                Status.SUCCESS -> {
                    _result._data?.let {

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
    private fun setCurrentWeather(apiResponse: ApiResponse){
        val current = apiResponse.current
        binding.apply {
            frameLayout.isVisible = true
            tvLocation.text = "Mumbai"
            tvShortWeather.text = current.condition.text
            tvCelcius.setTemperature2(current.temp_c)
            ivWeatherIcon.setImageResource(getWeatherIcon(current.is_day,current.condition.code))
            tvDateWithDay.text = myDateFormatter(apiResponse.location.localtime,"EEEE | dd MMM, yyyy ")

            btnNext.setOnClickListener {
                startActivity(Intent(requireActivity(),Days::class.java))
            }
        }
    }
    //RecyclerView Description Details
    private fun setupRVDescription(){
        currentDayDetailAdapter = CurrentDayDetailAdapter()
        binding.rvDescriptionList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            adapter = currentDayDetailAdapter
        }
    }
    //RecyclerView Hours
    private fun setupRVHours(){
        hourlyTempAdapter = HourlyTempAdapter()
        binding.rvTodayTimeList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            adapter = hourlyTempAdapter
        }
    }

    private fun generatePropertiesByCurrentWeather(current: Current):ArrayList<ModelKeyValue>{
        val propertiesList =ArrayList<ModelKeyValue>()

        propertiesList.add(ModelKeyValue("Humidity",current.humidity.toString()+"%",R.drawable.sun_6))
        propertiesList.add(ModelKeyValue("Wind",current.wind_kph.toString()+"km/h",R.drawable.moon_1))
        propertiesList.add(ModelKeyValue("Visibility",current.vis_km.toString()+"km",R.drawable.cloud_122))
        propertiesList.add(ModelKeyValue("UV",current.uv.toString(),R.drawable.sun_26))
        propertiesList.add(ModelKeyValue("Precip",current.precip_mm.toString()+"mm",R.drawable.cloud_302))
        propertiesList.add(ModelKeyValue("Wind Direction",current.wind_dir,R.drawable.wind_3d))
//        propertiesList.add(ModelKeyValue("Snow",forecastday.day.maxwind_kph.toString()+"%",R.drawable.snow))

        return propertiesList
    }


    private fun displayError(it: String) {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private fun displayLoading(status: Boolean) {
        binding.loader.isVisible = status
    }

}