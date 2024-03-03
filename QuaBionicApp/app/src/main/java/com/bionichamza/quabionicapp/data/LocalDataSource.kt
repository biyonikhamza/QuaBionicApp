package com.bionichamza.quabionicapp.data

import com.bionichamza.quabionicapp.data.database.ProstheticsDAO
import com.bionichamza.quabionicapp.data.database.entities.FavoriteEntity
import com.bionichamza.quabionicapp.data.database.entities.InspirationEntity
import com.bionichamza.quabionicapp.data.database.entities.ProstheticsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val prostheticDao : ProstheticsDAO
) {

    fun readProsthetics() : Flow<List<ProstheticsEntity>> {
        return prostheticDao.readProsthetics()
    }

    fun readFavoriteProsthetics() : Flow<List<FavoriteEntity>> {
        return prostheticDao.readFavoriteProsthetics()
    }

    fun readInspiration() : Flow<List<InspirationEntity>> {
        return prostheticDao.readInspiration()
    }

    suspend fun insertProsthetics(prostheticsEntity : ProstheticsEntity) {
        prostheticDao.insertProsthetics(prostheticsEntity)
    }

    suspend fun insertFavoriteProsthetics(favoriteEntity: FavoriteEntity) {
        prostheticDao.insertFavoriteProsthetics(favoriteEntity)
    }

    suspend fun insertInspiration(inspirationEntity: InspirationEntity) {
        prostheticDao.insertInspiration(inspirationEntity)
    }

    suspend fun deleteFavoriteProsthetic(favoriteEntity: FavoriteEntity) {
        prostheticDao.deleteFavoriteProsthetic(favoriteEntity)
    }

    suspend fun deleteAllFavoriteProsthetics() {
        prostheticDao.deleteAllFavoriteProsthetic()
    }


}