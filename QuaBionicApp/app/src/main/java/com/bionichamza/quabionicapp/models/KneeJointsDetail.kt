package com.bionichamza.quabionicapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class KneeJointsDetail(
    @SerializedName("description")
    val description: String,
    @SerializedName("distalConnection")
    val distalConnection: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("indication1")
    val indication1: String,
    @SerializedName("indication2")
    val indication2: String,
    @SerializedName("indication3")
    val indication3: String,
    @SerializedName("kneeFlexionAngle")
    val kneeFlexionAngle: String,
    @SerializedName("material")
    val material: String,
    @SerializedName("maximumBodyWeight")
    val maximumBodyWeight: String,
    @SerializedName("mobilityDegree")
    val mobilityDegree: String,
    @SerializedName("property1")
    val property1: String,
    @SerializedName("property2")
    val property2: String,
    @SerializedName("property3")
    val property3: String,
    @SerializedName("proximalConnection")
    val proximalConnection: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("systemHeight")
    val systemHeight: String,
    @SerializedName("weight")
    val weight: String
): Parcelable