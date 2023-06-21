package com.example.europecountrylist.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.europecountrylist.databinding.FragmentAllDataBinding
import com.example.europecountrylist.model.Country
import com.example.europecountrylist.view.adapters.CountryListAdapter
import com.example.europecountrylist.viewmodel.ListViewModel

class FragmentAllData : Fragment(), CountryListAdapter.OnItemClickListener {

    var filter: Array<String>? = null
    var sortCriteria: String? = null
    var ascending: Int = -1

    lateinit var viewModel: ListViewModel
    private val countriesAdapter = CountryListAdapter(arrayListOf(), this)
    lateinit var binding: FragmentAllDataBinding
    private val args: FragmentAllDataArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentAllDataBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            ascending = args.ascending
            sortCriteria = args.sortType

            if (args.filters!!.isNotEmpty()){
                filter = args.filters
                binding.tvFilterNumbers.text = args.filters!!.size.toString()
                binding.tvFilterNumbers.visibility = View.VISIBLE
            }

        } catch (e: Exception){
            Log.e("ERROR PARSING", e.toString(), )
        }

        binding.svCountry.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.filterItems(newText)
                return true
            }
        })

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            viewModel.refresh(filter, ascending, sortCriteria)
        }


        binding.buttonFilter.setOnClickListener {

            val direction = FragmentAllDataDirections.actionFragmentAllDataToFragmentFilter(filter, ascending, sortCriteria)
            findNavController().navigate(direction)

        }

        binding.buttonSort.setOnClickListener {

            val direction = FragmentAllDataDirections.actionFragmentAllDataToFragmentSort(filter, ascending, sortCriteria)
            findNavController().navigate(direction)
        }

        viewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)


        viewModel.refresh(filter, ascending, sortCriteria)

        binding.countriesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        observeViewModel()
    }

    fun observeViewModel() {

        viewModel.countries.observe(viewLifecycleOwner) { countries ->
            binding.countriesList.visibility = View.VISIBLE
            countries?.let {
                countriesAdapter.updateCountries(it)
            }
        }

        viewModel.countryLoadError.observe(viewLifecycleOwner) { isError ->
            isError?.let { binding.listError.visibility = if (it) View.VISIBLE else View.GONE }
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            isLoading?.let {
                binding.loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    binding.listError.visibility = View.GONE
                    binding.countriesList.visibility = View.GONE
                }
            }
        }

        viewModel._filteredItems.observe(viewLifecycleOwner) { items ->
            countriesAdapter.updateCountries(items)
        }


    }

    override fun onItemClicked(country: Country) {
        val action = FragmentAllDataDirections.actionFragmentAllDataToFragmentDetails(country)
        findNavController().navigate(action )
    }

}