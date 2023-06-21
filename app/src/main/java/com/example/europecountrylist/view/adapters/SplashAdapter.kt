package com.example.europecountrylist.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.europecountrylist.view.FragmentOnboarding1
import com.example.europecountrylist.view.FragmentOnboarding2
import com.example.europecountrylist.view.FragmentOnboarding3
import com.example.europecountrylist.view.FragmentOnboarding4

class SplashAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {


    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        if (position == 0)
            return FragmentOnboarding1()
        else if (position == 1)
            return FragmentOnboarding2()
        else if (position == 2)
            return FragmentOnboarding3()
        else
            return FragmentOnboarding4()
    }
}
