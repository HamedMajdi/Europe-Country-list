package com.example.europecountrylist.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.europecountrylist.databinding.FragmentOnboarding4Binding


class FragmentOnboarding4 : Fragment() {


    lateinit var binding: FragmentOnboarding4Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnboarding4Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStart.setOnClickListener {
            val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.apply{
                putBoolean("isChecked", binding.checkBox.isChecked)
            }.apply()

            val direction = FragmentSplashDirections.actionFragmentSplashToFragmentAllData(null, -1, null)
            findNavController().navigate(direction)
        }
    }

}