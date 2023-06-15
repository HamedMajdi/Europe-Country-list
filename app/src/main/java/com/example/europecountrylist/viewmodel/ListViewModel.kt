package com.example.europecountrylist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.europecountrylist.model.CountriesService
import com.example.europecountrylist.model.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class ListViewModel : ViewModel() {

    private val countriesService = CountriesService()

    // disposable uses RxJava to get the information from server. Then when the viewModel is closed,
    // we will close that connection
    private val disposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    // items hold the complete list of items fetched from the server
    private val _filteredItems = MutableLiveData<List<Country>>()
    val filteredItems: LiveData<List<Country>> = _filteredItems


    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true

        disposable.add(
            countriesService.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Country>>() {
                    override fun onSuccess(value: List<Country>?) {
                        countries.value = value
                        filterItems("")
                        countryLoadError.value = false
                        loading.value = false

                    }

                    override fun onError(e: Throwable?) {
                        countryLoadError.value = true
                        loading.value = false
                        Log.d("ERROR", e.toString())
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun filterItems(query: String) {
        val originalList = countries.value ?: emptyList()

        val filteredList = if (query.isNotBlank()) {
            originalList.filter { item ->
                item.countryName.finalName.contains(query, ignoreCase = true)
            }
        } else {
            originalList
        }

        _filteredItems.value = filteredList
    }
}