@file:Suppress("DEPRECATED_ANNOTATION")

package com.example.europecountrylist.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country(
    @SerializedName("name")
    val countryName: Name,

    @SerializedName("capital")
    val capital: List<String>?,

    @SerializedName("borders")
    val borders: List<String>?,


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
    ): Parcelable

@Parcelize
data class Currencies(
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String,
): Parcelable

@Parcelize
data class Name(
    @SerializedName("common")
    val finalName: String
): Parcelable


@Parcelize
data class Flag(
    @SerializedName("png")
    val finalPhoto: String
): Parcelable


@Parcelize
data class NumberCode(

    @SerializedName("root")
    val root: String,

    @SerializedName("suffixes")
    val suffix: List<String>?,
): Parcelable
