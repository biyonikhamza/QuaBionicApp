package com.bionichamza.quabionicapp.data

import android.util.JsonToken
import com.bionichamza.quabionicapp.data.network.ProstheticsAPI
import com.bionichamza.quabionicapp.models.InspirationWord
import com.bionichamza.quabionicapp.models.Prosthetics
import com.bionichamza.quabionicapp.models.ProstheticsInfo
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val prostheticsAPI: ProstheticsAPI
) {

    suspend fun getProstheticsInfo(queries: Map<String, String>) : Response<ProstheticsInfo> {
        return prostheticsAPI.getProstheticsInfo(queries)
    }
    suspend fun getProsthetics(queries : Map<String , String>) : Response<Prosthetics> {
        return prostheticsAPI.getProsthetics(queries)
    }

    suspend fun searchProstheticsInfo(searchQuery: Map<String, String>) : Response<ProstheticsInfo> {
        return prostheticsAPI.searchProstheticsInfo(searchQuery)
    }

    suspend fun searchProsthetics(searchQuery : Map<String , String>) : Response<Prosthetics> {
        return prostheticsAPI.searchProsthetics(searchQuery)
    }

    suspend fun getInspiration(queries: Map<String, String>) : Response<InspirationWord> {
        return prostheticsAPI.getInspiration(queries)
    }

}