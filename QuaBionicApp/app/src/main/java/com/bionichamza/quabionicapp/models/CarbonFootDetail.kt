package com.bionichamza.quabionicapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarbonFootDetail(
    @SerializedName("bodyWeight")
    val bodyWeight: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("heelHeight")
    val heelHigh: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("mobilityGrade")
    val mobilityGrade: String,
    @SerializedName("series")
    val series: String,
    @SerializedName("side")
    val side: String,
    @SerializedName("size")
    val size: String
): Parcelable