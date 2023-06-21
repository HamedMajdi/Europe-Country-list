package com.example.europecountrylist.model

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    val countryName: Name,

    @SerializedName("capital")
    val capital: List<String>?,

    @SerializedName("flags")
    val flag: Flag?,

    @SerializedName("idd")
    val phone: NumberCode?,

    @SerializedName("area")
    val area: Double?,

    @SerializedName("population")
    val population: Int?,

    @SerializedName("subregion")
    val subRegion: String?,

    @SerializedName("currencies")
    val currency: Map<String, Currencies>,

    @SerializedName("languages")
    val languages: Map<String, String>,
    )

data class Currencies(
    @SerializedName("name")
    val id: String,
    @SerializedName("symbol")
    val bucket: String,
)

data class Name(
    @SerializedName("common")
    val finalName: String
)

data class Flag(
    @SerializedName("png")
    val finalPhoto: String
)

data class NumberCode(

    @SerializedName("root")
    val root: String,

    @SerializedName("suffixes")
    val suffix: List<String>?,
)
