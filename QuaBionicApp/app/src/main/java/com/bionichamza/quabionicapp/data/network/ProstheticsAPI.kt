package com.bionichamza.quabionicapp.data.network

import android.util.JsonToken
import com.bionichamza.quabionicapp.models.InspirationWord
import com.bionichamza.quabionicapp.models.Prosthetics
import com.bionichamza.quabionicapp.models.ProstheticsInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ProstheticsAPI {

    // Url -> biyonikhamza/QuaBionicApp/main/Prosthetics.json
    // Base-URl -> https://raw.githubusercontent.com/

    @GET("biyonikhamza/@uaBionicApp/main/prostheticsInfo.json")
    suspend fun getProstheticsInfo(
        @QueryMap queries : Map<String , String>
    ) : Response<ProstheticsInfo>

    @GET("biyonikhamza/QuaBionicApp/main/Prosthetics.json")
    suspend fun getProsthetics(
        @QueryMap queries : Map<String, String>
    ) : Response<Prosthetics>

    @GET("biyonikhamza/@uaBionicApp/main/prostheticsInfo.json")
    suspend fun searchProstheticsInfo(
        @QueryMap searchQuery : Map<String , String>
    ) : Response<ProstheticsInfo>

    @GET("biyonikhamza/QuaBionicApp/main/Prosthetics.json")
    suspend fun searchProsthetics(
        @QueryMap searchQuery: Map<String , String>
    ) : Response<Prosthetics>

    @GET("biyonikhamza/QuaBionicApp/main/Prosthetics.json")
    suspend fun getInspiration(
        @QueryMap queries: Map<String, String>
    ):Response<InspirationWord>
}