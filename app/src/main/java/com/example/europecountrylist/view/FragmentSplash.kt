package com.example.europecountrylist.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.europecountrylist.databinding.FragmentSplashBinding
import com.example.europecountrylist.view.adapters.SplashAdapter

class FragmentSplash : Fragment() {


    lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // We define animations to each view according to the code below:
        binding.ivBackground.animate().translationY(-3600f).setDuration(1000).startDelay = 4000
        binding.ivLogo.animate().translationY(2800f).setDuration(1000).startDelay = 4000
        binding.lottie.animate().translationY(2800f).setDuration(1000).startDelay = 4000


        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val isChecked = sharedPreferences.getBoolean("isChecked", false)

        if (isChecked){
            Handler().postDelayed({

                val direction = FragmentSplashDirections.actionFragmentSplashToFragmentAllData(null, -1, null)
                findNavController().navigate(direction)

            }, 4300)
        } else{
            val adapter = SplashAdapter(parentFragmentManager)
            binding.pager.adapter = adapter

        }


    }

}