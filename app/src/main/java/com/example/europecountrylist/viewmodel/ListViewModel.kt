package com.example.europecountrylist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.europecountrylist.model.Country

class ListViewModel: ViewModel() {

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        fetchCountries()
    }

    private fun fetchCountries() {
        val mockData = listOf(Country(countryName = "C1"),
            Country(countryName = "C1"),
            Country(countryName = "C2"),
            Country(countryName = "C3"),
            Country(countryName = "C4"),
            Country(countryName = "C5"),
            Country(countryName = "C6"),
            Country(countryName = "C7"),
            Country(countryName = "C8"),
            Country(countryName = "C11"),
            Country(countryName = "C22"),
            Country(countryName = "C33"),
            Country(countryName = "C44"),
            Country(countryName = "C55"),
            Country(countryName = "C66"),
            Country(countryName = "C77"),
            Country(countryName = "C88")
        )

        countryLoadError.value = false
        loading.value = false
        countries.value = mockData
    }

}