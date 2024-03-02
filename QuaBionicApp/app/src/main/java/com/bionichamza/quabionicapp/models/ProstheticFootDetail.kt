package com.bionichamza.quabionicapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProstheticFootDetail(
    @SerializedName("Weight")
    val weight: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("heelHight")
    val heelHigh: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("maxBodyWeight")
    val maxBodyWeight: String,
    @SerializedName("mobilityGrade")
    val mobilityGrade: String,
    @SerializedName("serie")
    val series: String,
    @SerializedName("side")
    val side: String,
    @SerializedName("size")
    val size: String,
    @SerializedName("systemHight")
    val systemHigh: String
): Parcelable