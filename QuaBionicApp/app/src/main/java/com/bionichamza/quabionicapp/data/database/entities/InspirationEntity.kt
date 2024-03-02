package com.bionichamza.quabionicapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bionichamza.quabionicapp.models.Result
import com.bionichamza.quabionicapp.util.Constants.Companion.INSPIRATION_TABLE

@Entity(tableName = INSPIRATION_TABLE)
class InspirationEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var result : Result
)