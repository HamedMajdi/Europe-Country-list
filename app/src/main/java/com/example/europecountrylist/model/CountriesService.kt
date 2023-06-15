package com.example.europecountrylist.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Inject



class CountriesService {

    private val BASE_URL = "https://restcountries.com"
    private val api: CountriesAPI


    init {
        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountriesAPI::class.java)
    }


    fun getCountries(): Single<List<Country>> {
        return api.getCountries()
    }
}