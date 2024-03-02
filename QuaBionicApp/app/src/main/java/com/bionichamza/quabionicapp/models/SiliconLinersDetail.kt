package com.bionichamza.quabionicapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SiliconLinersDetail(
    @SerializedName("Circumference")
    val circumference: String,
    @SerializedName("connection")
    val connection: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("length")
    val length: String,
    @SerializedName("material")
    val material: String,
    @SerializedName("property1")
    val property1: String,
    @SerializedName("property2")
    val property2: String,
    @SerializedName("property3")
    val property3: String,
    @SerializedName("property4")
    val property4: String,
    @SerializedName("property5")
    val property5: String,
    @SerializedName("property6")
    val property6: String,
    @SerializedName("size")
    val size: String,
    @SerializedName("summary")
    val summary: String
): Parcelable