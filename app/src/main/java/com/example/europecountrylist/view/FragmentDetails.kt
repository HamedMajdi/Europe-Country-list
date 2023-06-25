package com.example.europecountrylist.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.europecountrylist.databinding.FragmentDetailsBinding
import com.example.europecountrylist.utils.getProgressDrawable
import com.example.europecountrylist.utils.loadImage
import java.text.DecimalFormat

class FragmentDetails : Fragment() {

    lateinit var binding: FragmentDetailsBinding
    private val args: FragmentDetailsArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dec = DecimalFormat("#,###")

        val name = args.countryDetails.countryName.finalName
        val capital = args.countryDetails.capital?.get(0)
        val phone = args.countryDetails.phone?.root+ args.countryDetails.phone?.suffix?.get(0)
        val area = dec.format(args.countryDetails.area)
        val population = dec.format(args.countryDetails.population)



        val subRegion = args.countryDetails.subRegion!!

        binding.ivDetailsFlag.loadImage(args.countryDetails.flag?.finalPhoto, getProgressDrawable(view.context))
        binding.tvDetailsName.text = name
        binding.tvDetailsCapital.text = capital
        binding.tvDetailsPhone.text = phone
        binding.tvDetailsArea.text = area + "km²"
        binding.tvDetailsPopulation.text = population
        binding.tvDetailsSubregion.text = subRegion

        var currency = ""
        val keysCurrency = args.countryDetails.currency.keys
        for (i in keysCurrency.indices){

            currency += keysCurrency.elementAt(i) + "   (" + args.countryDetails.currency.values.elementAt(i).name +")"
            if ((i - keysCurrency.size) > 0){
                currency += "\n"
            }
        }

        binding.tvDetailsCurrency.text = currency

        var languages = ""
        val keysLang = args.countryDetails.languages.keys
        for (i in keysLang.indices){

            languages += keysLang.elementAt(i) + "   (" + args.countryDetails.languages.values.elementAt(i) +")"
            if ((i - keysLang.size) > 0){
                languages += "\n"
            }
        }

        binding.tvDetailsLanguage.text = languages


        var borders = ""
        for (i in 0 until (args.countryDetails.borders?.size ?: 0)){

            borders += args.countryDetails.borders!!.get(i)
            if ((i - keysLang.size) > 0){
                borders += "\n"
            }
        }
        binding.tvDetailsBorder.text = borders

        val firstLanguage = args.countryDetails.languages.values.elementAt(0)
        val text = "$name is a European country located in $subRegion. " +
                "It has a population of $population people and covers an area of $area km². " +
                "The capital of $name is $capital, and the official language spoken is $firstLanguage."

        binding.tvBrief.text = text


    }
}