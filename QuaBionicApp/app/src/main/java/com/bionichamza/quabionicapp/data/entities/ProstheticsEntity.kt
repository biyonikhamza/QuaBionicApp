package com.bionichamza.quabionicapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bionichamza.quabionicapp.models.ProstheticsItem
import com.bionichamza.quabionicapp.util.Constants.PROSTHETICS_TABLE

@Entity(tableName = PROSTHETICS_TABLE)
class ProstheticsEntity(
    var prostheticsItem: ProstheticsItem
) {
    @PrimaryKey(autoGenerate = false)
    var id : Int = 0
}