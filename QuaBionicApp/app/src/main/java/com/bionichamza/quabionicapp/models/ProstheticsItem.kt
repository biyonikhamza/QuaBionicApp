package com.bionichamza.quabionicapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProstheticsItem(
    @SerializedName("carbonFoot")
    val carbonFoot: String,
    @SerializedName("kneeJoints")
    val kneeJoints: String,
    @SerializedName("prostheticFoot")
    val prostheticFoot: String,
    @SerializedName("siliconeLiners")
    val siliconeLiners: String
) : Parcelable