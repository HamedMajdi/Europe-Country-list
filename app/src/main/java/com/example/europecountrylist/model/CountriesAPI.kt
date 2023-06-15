package com.example.europecountrylist.model

import io.reactivex.Single
import retrofit2.http.GET

//Defines the function that is going to get called in order to retrieve the information from the server
interface CountriesAPI {

    //    @GET("DevTides/countries/master/countriesV2.json")

    @GET("v3.1/region/europe")
    fun getCountries(): Single<List<Country>>
}