package com.bionichamza.quabionicapp.models

import com.google.gson.annotations.SerializedName

class ProstheticsInfoResult(
    @SerializedName("prostheticsInfoResults")
    val prostheticsInfoResults : List<ProstheticsInfo>
)