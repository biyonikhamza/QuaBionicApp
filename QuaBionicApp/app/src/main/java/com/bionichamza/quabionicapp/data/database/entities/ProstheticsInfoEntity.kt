package com.bionichamza.quabionicapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bionichamza.quabionicapp.models.ProstheticsInfo
import com.bionichamza.quabionicapp.util.Constants.Companion.PROSTHETICS_INFO_TABLE

@Entity(tableName = PROSTHETICS_INFO_TABLE)
class ProstheticsInfoEntity(
    val prostheticsInfo: ProstheticsInfo
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}


