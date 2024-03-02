package com.bionichamza.quabionicapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bionichamza.quabionicapp.models.Result
import com.bionichamza.quabionicapp.util.Constants.Companion.FAVORITE_TABLE

@Entity(tableName = FAVORITE_TABLE)
class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var result: Result
)