package com.bionichamza.quabionicapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bionichamza.quabionicapp.data.database.entities.FavoriteEntity
import com.bionichamza.quabionicapp.data.database.entities.InspirationEntity
import com.bionichamza.quabionicapp.data.database.entities.ProstheticsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProstheticsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProsthetics(prostheticsEntity: ProstheticsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInspiration(inspirationEntity: InspirationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteProsthetics(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM prosthetics_table ORDER BY id ASC")
    fun readProsthetics() : Flow<List<ProstheticsEntity>>

    @Query("SELECT * FROM inspiration_table ORDER BY id ASC")
    fun readInspiration() : Flow<List<InspirationEntity>>

    @Query("SELECT * FROM favorite_table ORDER BY id ASC")
    fun readFavoriteProsthetics() : Flow<List<FavoriteEntity>>

    @Delete
    suspend fun deleteFavoriteProsthetic(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM favorite_table")
    suspend fun deleteAllFavoriteProsthetic()

}