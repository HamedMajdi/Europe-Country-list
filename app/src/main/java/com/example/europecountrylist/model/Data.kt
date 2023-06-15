package com.example.europecountrylist.model

import com.google.gson.Gson
import com.google.gson.JsonElement
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

    @SerializedName("subregion")
    val subRegion: String?
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