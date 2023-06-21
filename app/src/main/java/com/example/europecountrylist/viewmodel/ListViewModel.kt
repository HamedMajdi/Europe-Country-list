package com.example.europecountrylist.viewmodel

import android.util.Log
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
    val _filteredItems = MutableLiveData<List<Country>>()
//    val filteredItems: LiveData<List<Country>> = _filteredItems


    val _sortedItems = MutableLiveData<List<Country>>()
//    val sortedItems: LiveData<List<Country>> = _sortedItems

    fun refresh(filters: Array<String>?, ascending: Int, sortCriteria: String?) {
        fetchCountries(filters, ascending, sortCriteria)
    }

    private fun fetchCountries(filters: Array<String>?, ascending: Int, sortCriteria: String?) {
        loading.value = true

        disposable.add(
            countriesService.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                    override fun onSuccess(value: List<Country>?) {

                        if (!filters.isNullOrEmpty()) {
                            var filteredList = listOf<Country>()

                            for (i in 0..filters.size - 1) {
                                filteredList += value!!.filter {
                                    it.subRegion!!.contains(filters.get(i))
                                }
                            }
                            countries.value = filteredList

                        } else {
                            countries.value = value!!
                        }


                        val currentItems = countries.value ?: emptyList()
                        var sortedList = listOf<Country>()
                        if (ascending == 1) {
                            if (sortCriteria == "name") {
                                sortedList = currentItems.sortedByDescending { item ->
                                    item.countryName.finalName
                                }
                            } else if (sortCriteria == "area") {
                                sortedList = currentItems.sortedBy { item ->
                                    item.area
                                }
                            } else if (sortCriteria == "population") {
                                sortedList = currentItems.sortedBy { item ->
                                    item.population
                                }
                            }
                            countries.value = sortedList
                        }
                        else if (ascending == 0) {
                            if (sortCriteria == "name") {
                                sortedList = currentItems.sortedBy { item ->
                                    item.countryName.finalName
                                }
                            } else if (sortCriteria == "area") {
                                sortedList = currentItems.sortedByDescending { item ->
                                    item.area
                                }
                            } else if (sortCriteria == "population") {
                                sortedList = currentItems.sortedByDescending { item ->
                                    item.population
                                }
                            }
                            countries.value = sortedList

                        }

                        filterItems("")
                        countryLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        countryLoadError.value = true
                        loading.value = false
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
//                item.subRegion!!.contains(query, ignoreCase = true)
            }
        } else {
            originalList
        }
        _filteredItems.value = filteredList
    }


    fun sortItemsByName(metric: String, ascending: Boolean) {
        val currentItems = countries.value ?: emptyList()

        val sortedList = currentItems.sortedBy { item ->
            item.area
        }
        _sortedItems.value = sortedList
    }
}