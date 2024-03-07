package com.bionichamza.quabionicapp.data.network

import android.util.JsonToken
import com.bionichamza.quabionicapp.models.InspirationWord
import com.bionichamza.quabionicapp.models.Prosthetics
import com.bionichamza.quabionicapp.models.ProstheticsInfoS
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ProstheticsAPI {

    // Url -> biyonikhamza/QuaBionicApp/main/Prosthetics.json
    // Base-URl -> https://raw.githubusercontent.com/

    @GET("/biyonikhamza/QuaBionicApp/main/Prosthetics.json")
    suspend fun getProsthetics(
        @QueryMap queries : Map<String, String>
    ) : Response<Prosthetics>

    @GET("/biyonikhamza/QuaBionicApp/main/Prosthetics.json")
    suspend fun searchProsthetics(
        @QueryMap searchQuery: Map<String , String>
    ) : Response<Prosthetics>

    @GET("/biyonikhamza/QuaBionicApp/main/prostheticsInfo.json")
    suspend fun getProstheticsInfo(
        @QueryMap queriesPInfo : Map<String, String>
    ) : Response<ProstheticsInfoS>

    @GET("/biyonikhamza/QuaBionicApp/main/prostheticsInfo.json")
    suspend fun searchProstheticsInfo(
        @QueryMap searchQueryInfo : Map<String , String>
    ) : Response<ProstheticsInfoS>

    @GET("/biyonikhamza/QuaBionicApp/main/Prosthetics.json")
    suspend fun getInspiration(
        @Query("token") token: JsonToken
    ):Response<InspirationWord>
}