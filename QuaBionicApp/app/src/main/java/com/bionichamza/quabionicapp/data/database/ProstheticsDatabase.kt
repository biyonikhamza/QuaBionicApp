package com.bionichamza.quabionicapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bionichamza.quabionicapp.data.database.entities.FavoriteEntity
import com.bionichamza.quabionicapp.data.database.entities.InspirationEntity
import com.bionichamza.quabionicapp.data.database.entities.ProstheticsEntity

@Database(
    entities = [ProstheticsEntity::class , FavoriteEntity::class , InspirationEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ProstheticsDatabase : RoomDatabase() {

    abstract fun prostheticsDao() : ProstheticsDAO

}