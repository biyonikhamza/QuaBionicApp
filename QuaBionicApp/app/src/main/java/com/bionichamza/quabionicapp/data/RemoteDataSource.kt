package com.bionichamza.quabionicapp.data

import com.bionichamza.quabionicapp.data.network.ProstheticsAPI
import com.bionichamza.quabionicapp.models.InspirationWord
import com.bionichamza.quabionicapp.models.Prosthetics
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val prostheticsAPI: ProstheticsAPI
) {

    suspend fun getProsthetics(queries : Map<String , String>) : Response<Prosthetics> {
        return prostheticsAPI.getProsthetics(queries)
    }

    suspend fun searchRecipes(searchQuery : Map<String , String>) : Response<Prosthetics> {
        return prostheticsAPI.searchProsthetics(searchQuery)
    }

    suspend fun getInspiration(apiKey : String) : Response<InspirationWord> {
        return prostheticsAPI.getInspiration(apiKey)
    }

}