package com.example.europecountrylist.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.europecountrylist.R
import com.example.europecountrylist.model.Country
import com.example.europecountrylist.utils.getProgressDrawable
import com.example.europecountrylist.utils.loadImage

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
        private val imageView: ImageView = view.findViewById(R.id.imageView)
        private val countryCapital: TextView = view.findViewById(R.id.capital)
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(country: Country) {
            countryName.text = country.countryName.finalName
            countryCapital.text = country.capital?.get(0) ?: "NOT FOUND"
            imageView.loadImage(country.flag?.finalPhoto, progressDrawable)
        }
    }
}