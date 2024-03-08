package com.bionichamza.quabionicapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProstheticsInfoItem(
    @SerializedName("carbonFoot")
    val carbonFoot: String,
    @SerializedName("kneeJoints")
    val kneeJoints: String,
    @SerializedName("prostheticFoot")
    val prostheticFoot: String,
    @SerializedName("siliconLiners")
    val siliconeLiners: String
) : Parcelable