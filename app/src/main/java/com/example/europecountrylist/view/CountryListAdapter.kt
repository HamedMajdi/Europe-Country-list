package com.example.europecountrylist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.europecountrylist.R
import com.example.europecountrylist.model.Country

class CountryListAdapter(var countries: ArrayList<Country>): RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    fun updateCountries(newCountries: List<Country>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }


    class CountryViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val countryName: TextView = view.findViewById(R.id.name)
//        private val imageView = view.imageView
//        private val countryCapital = view.capital
//        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(country: Country) {
            countryName.text = country.countryName
//            countryCapital.text = country.capital
//            imageView.loadImage(country.flag, progressDrawable)
        }
    }
}