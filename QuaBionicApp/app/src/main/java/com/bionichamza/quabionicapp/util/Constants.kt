package com.bionichamza.quabionicapp.util

class Constants {

    // https://raw.githubusercontent.com/biyonikhamza/QuaBionicApp/main/Prosthetics.json?token=GHSAT0AAAAAACNXUXVLPYNHEDHMVCPQSNBYZPFYQIA
    companion object{

        const val BASE_URL = "https://raw.githubusercontent.com/"
        const val API_KEY = ""

        // Room Database
        const val DATABASE_NAME = "prosthetic_database"
        const val PROSTHETICS_TABLE = "prosthetics_table"
        const val INSPIRATION_TABLE = "inspiration_table"
        const val FAVORITE_TABLE = "favorite_table"
        const val PROSTHETICS_INFO_TABLE = "prosthetics_info_table"

        const val PREFERENCES_NAME = "prosthetic_preferences"
        const val PREFERENCES_BACK_ONLINE = "backOnline"

        // API Query Keys
        const val QUERY_SEARCH = "query"
        const val QUERY_TOKEN_KEY = "token"

    }
}