package com.example.europecountrylist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.europecountrylist.R
import com.example.europecountrylist.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    private val countriesAdapter = CountryListAdapter(arrayListOf())
    lateinit var rvCountriesList: RecyclerView
    lateinit var tvListError: TextView
    lateinit var pbLoading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCountriesList = findViewById(R.id.countriesList)
        tvListError = findViewById(R.id.list_error)
        pbLoading = findViewById(R.id.loading_view)


        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(ListViewModel::class.java)
        viewModel.refresh()

        rvCountriesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.countries.observe(this, Observer { countries ->
            countries?.let { countriesAdapter.updateCountries(it) }
        })

        viewModel.countryLoadError.observe(this, Observer { isError ->
            isError?.let { tvListError.visibility = if(it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                pbLoading.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    tvListError.visibility = View.GONE
                    rvCountriesList.visibility = View.GONE
                }
            }
        })
    }
}