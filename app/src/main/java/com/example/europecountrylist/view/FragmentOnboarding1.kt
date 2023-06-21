package com.example.europecountrylist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.europecountrylist.databinding.FragmentOnboarding1Binding

class FragmentOnboarding1 : Fragment() {

    lateinit var binding: FragmentOnboarding1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnboarding1Binding.inflate(inflater, container, false)
        return binding.root
    }
}