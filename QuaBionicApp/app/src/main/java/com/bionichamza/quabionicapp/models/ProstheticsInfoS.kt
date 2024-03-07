package com.bionichamza.quabionicapp.models

import com.google.gson.annotations.SerializedName

data class ProstheticsInfoS(
    @SerializedName("carbonFoot")
    val carbonFoot: String,
    @SerializedName("kneeJoints")
    val kneeJoints: String,
    @SerializedName("prostheticFoot")
    val prostheticFoot: String,
    @SerializedName("siliconeLiners")
    val siliconeLiners: String
)