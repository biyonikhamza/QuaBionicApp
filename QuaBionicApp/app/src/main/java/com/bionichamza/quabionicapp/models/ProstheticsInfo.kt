package com.bionichamza.quabionicapp.models

import com.google.gson.annotations.SerializedName

data class ProstheticsInfo(
    @SerializedName("prostheticsInfoResults")
    val prostheticsInfoResults: List<ProstheticsInfoResult>
)