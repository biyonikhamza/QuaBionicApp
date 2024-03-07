package com.bionichamza.quabionicapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ProstheticsInfoResult(
    val prostheticsInfo: @RawValue List<ProstheticsInfoS>
) : Parcelable