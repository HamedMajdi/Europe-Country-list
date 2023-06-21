package com.example.europecountrylist.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.europecountrylist.databinding.FragmentFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.lang.Exception

class FragmentFilter : BottomSheetDialogFragment() {

    lateinit var binding: FragmentFilterBinding
    var isCheckedSEE = false
    var isCheckedSE = false
    var isCheckedNE = false
    var isCheckedEE = false
    var isCheckedWE = false
    var isCheckedCE = false
    private val args: FragmentFilterArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBinding.inflate(inflater, container, false)
//        binding.root.setBackgroundColor(getResources().getColor(android.R.color.white))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            if (args.filters!!.isNotEmpty()){
                for(i in 0 until args.filters!!.size){

                    when(args.filters!!.get(0)){
                        "Southeast Europe" -> {
                            binding.checkboxSee.setMinAndMaxFrame(30, 90)
                            binding.checkboxSee.playAnimation()
                            isCheckedSEE = true
                        }

                        "Southern Europe" -> {
                            binding.checkboxSe.setMinAndMaxFrame(30, 90)
                            binding.checkboxSe.playAnimation()
                            isCheckedSE = true
                        }

                        "Northern Europe" -> {
                            binding.checkboxNe.setMinAndMaxFrame(30, 90)
                            binding.checkboxNe.playAnimation()
                            isCheckedNE = true
                        }

                        "Eastern Europe" -> {
                            binding.checkboxEe.setMinAndMaxFrame(30, 90)
                            binding.checkboxEe.playAnimation()
                            isCheckedEE = true
                        }

                        "Western Europe" -> {
                            binding.checkboxWe.setMinAndMaxFrame(30, 90)
                            binding.checkboxWe.playAnimation()
                            isCheckedWE = true
                        }

                        "Central Europe" -> {
                            binding.checkboxCe.setMinAndMaxFrame(30, 90)
                            binding.checkboxCe.playAnimation()
                            isCheckedCE = true
                        }

                    }
                }
            }

        } catch (e: Exception){
            Log.e("ERROR PARSING", e.toString(), )
        }

        binding.btnFilter.setOnClickListener {

            var data = arrayOf<String>()
            if (isCheckedSEE) data+=("Southeast Europe")
            if (isCheckedSE) data+=("Southern Europe")
            if (isCheckedNE) data+=("Northern Europe")
            if (isCheckedEE) data+=("Eastern Europe")
            if (isCheckedWE) data+=("Western Europe")
            if (isCheckedCE) data+=("Central Europe")

            val direction = FragmentFilterDirections.actionFragmentFilterToFragmentAllData(data, args.ascending, args.sortType)
            findNavController().navigate(direction)

            dismiss()
        }



        binding.checkboxSee.setOnClickListener {
            clicked(1)
        }
        binding.tvSee.setOnClickListener {
            clicked(1)
        }


        binding.checkboxSe.setOnClickListener {
            clicked(2)
        }
        binding.tvSe.setOnClickListener {
            clicked(2)
        }



        binding.checkboxEe.setOnClickListener {
            clicked(3)
        }
        binding.tvEe.setOnClickListener {
            clicked(3)
        }



        binding.checkboxNe.setOnClickListener {
            clicked(4)
        }
        binding.tvNe.setOnClickListener {
            clicked(4)
        }



        binding.checkboxCe.setOnClickListener {
            clicked(5)
        }
        binding.tvCe.setOnClickListener {
            clicked(5)
        }

        binding.checkboxWe.setOnClickListener {
            clicked(6)
        }
        binding.tvWe.setOnClickListener {
            clicked(6)
        }

    }

    fun clicked(whichOne: Int) {

        when (whichOne) {
            1 -> if (!isCheckedSEE) {
                binding.checkboxSee.setMinAndMaxFrame(30, 90)
                binding.checkboxSee.playAnimation()
                isCheckedSEE = true
            } else {
                binding.checkboxSee.setMinAndMaxFrame(91, 150)
                binding.checkboxSee.playAnimation()
                isCheckedSEE = false
            }

            2 -> if (!isCheckedSE) {
                binding.checkboxSe.setMinAndMaxFrame(30, 90)
                binding.checkboxSe.playAnimation()
                isCheckedSE = true
            } else {
                binding.checkboxSe.setMinAndMaxFrame(91, 150)
                binding.checkboxSe.playAnimation()
                isCheckedSE = false
            }

            3 -> if (!isCheckedEE) {
                binding.checkboxEe.setMinAndMaxFrame(30, 90)
                binding.checkboxEe.playAnimation()
                isCheckedEE = true
            } else {
                binding.checkboxEe.setMinAndMaxFrame(91, 150)
                binding.checkboxEe.playAnimation()
                isCheckedEE = false
            }

            4 -> if (!isCheckedNE) {
                binding.checkboxNe.setMinAndMaxFrame(30, 90)
                binding.checkboxNe.playAnimation()
                isCheckedNE = true
            } else {
                binding.checkboxNe.setMinAndMaxFrame(91, 150)
                binding.checkboxNe.playAnimation()
                isCheckedNE = false
            }

            5 -> if (!isCheckedCE) {
                binding.checkboxCe.setMinAndMaxFrame(30, 90)
                binding.checkboxCe.playAnimation()
                isCheckedCE = true
            } else {
                binding.checkboxCe.setMinAndMaxFrame(91, 150)
                binding.checkboxCe.playAnimation()
                isCheckedCE = false
            }

            6 -> if (!isCheckedWE) {
                binding.checkboxWe.setMinAndMaxFrame(30, 90)
                binding.checkboxWe.playAnimation()
                isCheckedWE = true
            } else {
                binding.checkboxWe.setMinAndMaxFrame(91, 150)
                binding.checkboxWe.playAnimation()
                isCheckedWE = false
            }
        }



    }
}