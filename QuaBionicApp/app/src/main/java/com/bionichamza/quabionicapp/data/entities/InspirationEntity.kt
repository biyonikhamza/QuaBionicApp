package com.bionichamza.quabionicapp.data.entities

import androidx.room.PrimaryKey
import com.bionichamza.quabionicapp.models.Result

class InspirationEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var result : Result
)