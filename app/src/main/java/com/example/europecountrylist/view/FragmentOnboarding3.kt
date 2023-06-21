package com.example.europecountrylist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.europecountrylist.databinding.FragmentOnboarding3Binding


class FragmentOnboarding3 : Fragment() {


    lateinit var binding: FragmentOnboarding3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnboarding3Binding.inflate(inflater, container, false)
        return binding.root
    }

}