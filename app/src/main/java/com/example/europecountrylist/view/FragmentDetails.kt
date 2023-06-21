package com.example.europecountrylist.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dec = DecimalFormat("#,###")

        val name = args.countryDetails.countryName.finalName
        val capital = args.countryDetails.capital!!.get(0)
        val phone = args.countryDetails.phone!!.root+ args.countryDetails.phone!!.suffix!!.get(0)
        val area = dec.format(args.countryDetails.area)
        var population = dec.format(args.countryDetails.population)



        val subRegion = args.countryDetails.subRegion!!

        binding.ivDetailsFlag.loadImage(args.countryDetails.flag?.finalPhoto, getProgressDrawable(view.context))
        binding.tvDetailsName.setText(name)
        binding.tvDetailsCapital.setText(capital)
        binding.tvDetailsPhone.setText(phone)
        binding.tvDetailsArea.setText(area + "km²")
        binding.tvDetailsPopulation.setText(population)
        binding.tvDetailsSubregion.setText(subRegion)

        var currency = ""
        var keys_curr = args.countryDetails.currency.keys
        for (i in 0..keys_curr.size-1){

            currency += keys_curr.elementAt(i) + "   (" + args.countryDetails.currency.values.elementAt(i).name +")"
            if ((i - keys_curr.size) > 0){
                currency += "\n"
            }
        }

        binding.tvDetailsCurrency.setText(currency)

        var languages = ""
        var keys_lang = args.countryDetails.languages.keys
        for (i in 0..keys_lang.size-1){

            languages += keys_lang.elementAt(i) + "   (" + args.countryDetails.languages.values.elementAt(i) +")"
            if ((i - keys_lang.size) > 0){
                languages += "\n"
            }
        }

        binding.tvDetailsLanguage.setText(languages)


        var borders = ""
        for (i in 0..args.countryDetails.borders!!.size-1){

            borders += args.countryDetails.borders!!.get(i)
            if ((i - keys_lang.size) > 0){
                borders += "\n"
            }
        }
        binding.tvDetailsBorder.setText(borders)

        var firstLanguage = args.countryDetails.languages.values.elementAt(0)
        var text = "$name is a European country located in $subRegion. " +
                "It has a population of $population people and covers an area of $area km². " +
                "The capital of $name is $capital, and the official language spoken is $firstLanguage."

        binding.tvBrief.setText(text)


    }
}