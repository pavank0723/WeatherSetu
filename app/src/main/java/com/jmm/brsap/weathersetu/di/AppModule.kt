package com.jmm.brsap.weathersetu.di

import com.jmm.brsap.weathersetu.api.WeatherApi
import com.jmm.brsap.weathersetu.repository.WeatherRepo

import com.jmm.brsap.weathersetu.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideWeatherApi(retrofit: Retrofit):WeatherApi = retrofit.create(WeatherApi::class.java)

    @Singleton
    @Provides
    fun provideWeatherRepo(weatherApi: WeatherApi):WeatherRepo =WeatherRepo(weatherApi)
//    fun provideWeatherRepo(retrofit: Retrofit):WeatherRepo = retrofit.create(WeatherRepo::class.java)

}