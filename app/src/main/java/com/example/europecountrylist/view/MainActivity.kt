package com.example.europecountrylist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.europecountrylist.R
import com.example.europecountrylist.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    private val countriesAdapter = CountryListAdapter(arrayListOf())
    lateinit var rvCountriesList: RecyclerView
    lateinit var tvListError: TextView
    lateinit var pbLoading: ProgressBar
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var searchView: SearchView

    lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCountriesList = findViewById(R.id.countriesList)
        tvListError = findViewById(R.id.list_error)
        pbLoading = findViewById(R.id.loading_view)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)


        searchView = findViewById(R.id.sv_country)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.filterItems(newText)
                return true
            }
        })

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }


        button = findViewById(R.id.button2)
        button.setOnClickListener {

            Log.d("AAAAA: ", "CLICKED")
            viewModel.sortItemsByName()

        }
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)


        viewModel.refresh()

        rvCountriesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }


        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.countries.observe(this, Observer { countries ->
            rvCountriesList.visibility = View.VISIBLE
            countries?.let {
                countriesAdapter.updateCountries(it) }
        })

        viewModel.countryLoadError.observe(this, Observer { isError ->
            isError?.let { tvListError.visibility = if (it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                pbLoading.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    tvListError.visibility = View.GONE
                    rvCountriesList.visibility = View.GONE
                }
            }
        })

        viewModel.filteredItems.observe(this, { items ->
            countriesAdapter.updateCountries(items)
        })

        viewModel.sortedItems.observe(this, { items ->
            countriesAdapter.updateCountries(items)
        })
    }
}