package com.example.europecountrylist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.europecountrylist.databinding.FragmentOnboarding2Binding

class FragmentOnboarding2 : Fragment() {

    lateinit var binding: FragmentOnboarding2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnboarding2Binding.inflate(inflater, container, false)
        return binding.root
    }

}