package com.example.europecountrylist.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    val countryName: Name,

    @SerializedName("capital")
    val capital: List<String>?,

    @SerializedName("flags")
    val flag: Flag?
    )

data class Name(

    @SerializedName("common")
    val finalName: String
)

data class Flag(

    @SerializedName("png")
    val finalPhoto: String
)