package com.bionichamza.quabionicapp.models

import com.google.gson.annotations.SerializedName

data class Prosthetics(
    @SerializedName("results")
    val results: List<Result>
)