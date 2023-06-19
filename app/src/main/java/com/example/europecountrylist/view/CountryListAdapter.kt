package com.example.europecountrylist.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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

        private val countryName: TextView = view.findViewById(R.id.tv_row_country_name)
        private val region: TextView = view.findViewById(R.id.tv_sub_region)

        private val tvArea: TextView = view.findViewById(R.id.tv_area)
        private val phoneCode: TextView = view.findViewById(R.id.tv_phone_code)

        private val imageView: ImageView = view.findViewById(R.id.imageView)
        private val countryCapital: TextView = view.findViewById(R.id.tv_capital)
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(country: Country) {

            Log.d("GSON_TEST", country.currency.keys.toString())
            for ((key, value) in country.languages) {
                Log.d("GSON_TEST_LANG", "KEY: $key, VALUE: $value")
            }



            countryName.text = country.countryName.finalName
            countryCapital.text = country.capital?.get(0) ?: "NOT FOUND"
            region.text = country.subRegion

            val area = country.area
            if (area != null) {
                if (area % 1 == 0.0){
                    tvArea.text = (area.toInt()).toString() + " KM"
                } else {
                    tvArea.text = area.toString() + "KM"
                }
            }


            phoneCode.text = country.phone?.root + country.phone?.suffix?.get(0)


            imageView.loadImage(country.flag?.finalPhoto, progressDrawable)
        }
    }
}