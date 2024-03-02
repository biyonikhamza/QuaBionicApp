package com.bionichamza.quabionicapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bionichamza.quabionicapp.models.Prosthetics
import com.bionichamza.quabionicapp.util.Constants.PROSTHETICS_TABLE

@Entity(tableName = PROSTHETICS_TABLE)
class ProstheticsEntity(
    var prosthetics: Prosthetics
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}