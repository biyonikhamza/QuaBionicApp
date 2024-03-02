package com.bionichamza.quabionicapp.data.network

import com.bionichamza.quabionicapp.models.InspirationWord
import com.bionichamza.quabionicapp.models.Prosthetics
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ProstheticsAPI {

    // Url -> https://raw.githubusercontent.com/biyonikhamza/QuaBionicApp/main/Prosthetics.json?token=GHSAT0AAAAAACNXUXVLV5UWQFNEY3IPDFWIZPDDUEQ
    // Base-URl -> https://raw.githubusercontent.com/

    @GET("https://raw.githubusercontent.com/biyonikhamza/QuaBionicApp/main/Prosthetics.json?token=GHSAT0AAAAAACNXUXVLV5UWQFNEY3IPDFWIZPDDUEQ")
    suspend fun getProsthetics(
        @QueryMap queries : Map<String, String>
    ) : Response<Prosthetics>

    @GET("https://raw.githubusercontent.com/biyonikhamza/QuaBionicApp/main/Prosthetics.json?token=GHSAT0AAAAAACNXUXVLV5UWQFNEY3IPDFWIZPDDUEQ")
    suspend fun searchProsthetics(
        @QueryMap searchQuery: Map<String , String>
    ) : Response<Prosthetics>

    @GET("https://raw.githubusercontent.com/biyonikhamza/QuaBionicApp/main/Prosthetics.json?token=GHSAT0AAAAAACNXUXVLV5UWQFNEY3IPDFWIZPDDUEQ")
    suspend fun getInspiration(
        @Query("apiKey") apiKey : String
    ):Response<InspirationWord>
}