package com.example.europecountrylist.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.europecountrylist.R
import com.example.europecountrylist.databinding.FragmentSortBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FragmentSort : BottomSheetDialogFragment() {

    lateinit var binding: FragmentSortBinding
    private val args: FragmentSortArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSortBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            if (args.ascending == 1){
                when(args.sortType){
                    "name" -> binding.rbAlphaZa.isChecked = true
                    "area" -> binding.rbAreaLow.isChecked = true
                    "population" -> binding.rbPopulationLow.isChecked = true
                }
            }
            else if (args.ascending == 0){

                when(args.sortType){
                    "name" -> binding.rbAlphaAz.isChecked = true
                    "area" -> binding.rbAreaHigh.isChecked = true
                    "population" -> binding.rbPopulationHigh.isChecked = true
                }
            }


        } catch (e: Exception){
            Log.e("SORT", e.toString(), )
        }

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId){
                R.id.rb_alpha_az -> {
                    val direction = FragmentSortDirections
                        .actionFragmentSortToFragmentAllData(args.filters, 0, "name")
                    findNavController().navigate(direction)
                }

                R.id.rb_alpha_za -> {
                    val direction = FragmentSortDirections
                        .actionFragmentSortToFragmentAllData(args.filters, 1, "name")
                    findNavController().navigate(direction)
                }

                R.id.rb_area_high -> {
                    val direction = FragmentSortDirections
                        .actionFragmentSortToFragmentAllData(args.filters, 0, "area")
                    findNavController().navigate(direction)
                }

                R.id.rb_area_low -> {
                    val direction = FragmentSortDirections
                        .actionFragmentSortToFragmentAllData(args.filters, 1, "area")
                    findNavController().navigate(direction)
                }

                R.id.rb_population_high -> {
                    val direction = FragmentSortDirections
                        .actionFragmentSortToFragmentAllData(args.filters, 0, "population")
                    findNavController().navigate(direction)
                }

                R.id.rb_population_low -> {
                    val direction = FragmentSortDirections
                        .actionFragmentSortToFragmentAllData(args.filters, 1, "population")
                    findNavController().navigate(direction)
                }
            }
        }
    }

}