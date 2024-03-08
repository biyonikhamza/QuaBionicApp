package com.bionichamza.quabionicapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bionichamza.quabionicapp.data.database.entities.FavoriteEntity
import com.bionichamza.quabionicapp.data.database.entities.InspirationEntity
import com.bionichamza.quabionicapp.data.database.entities.ProstheticsEntity
import com.bionichamza.quabionicapp.data.database.entities.ProstheticsInfoEntity

@Database(
    entities = [ProstheticsEntity::class , FavoriteEntity::class , InspirationEntity::class , ProstheticsInfoEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ProstheticsTypeConverter::class)
abstract class ProstheticsDatabase : RoomDatabase() {

    abstract fun prostheticsDao() : ProstheticsDAO

}