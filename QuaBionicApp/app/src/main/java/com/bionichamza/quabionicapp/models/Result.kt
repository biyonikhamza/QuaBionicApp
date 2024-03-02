package com.bionichamza.quabionicapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Result(
    @SerializedName("prostheticsInfo")
    val prostheticsInfo: @RawValue List<ProstheticsInfo>,
    @SerializedName("prostheticFootDetails")
    val prostheticFootDetails: @RawValue List<ProstheticFootDetail>,
    @SerializedName("carbonFootDetails")
    val carbonFootDetails: @RawValue List<CarbonFootDetail>,
    @SerializedName("kneeJointsDetails")
    val kneeJointsDetails: @RawValue List<KneeJointsDetail>,
    @SerializedName("siliconLinersDetails")
    val siliconLinersDetails: @RawValue List<SiliconLinersDetail>,
    @SerializedName("inspirationWords")
    val inspirationWords: @RawValue List<InspirationWord>
) : Parcelable