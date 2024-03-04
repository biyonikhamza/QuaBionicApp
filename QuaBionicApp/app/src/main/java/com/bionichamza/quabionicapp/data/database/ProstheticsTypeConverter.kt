package com.bionichamza.quabionicapp.data.database

import androidx.room.TypeConverter
import com.bionichamza.quabionicapp.models.Prosthetics
import com.bionichamza.quabionicapp.models.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProstheticsTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun prostheticToString(prosthetics : Prosthetics) : String {
        return gson.toJson(prosthetics)
    }

    @TypeConverter
    fun stringToProsthetic(data : String): Prosthetics {
        val listType = object : TypeToken<Prosthetics>() {}.type
        return gson.fromJson(data , listType)
    }

    @TypeConverter
    fun resultToString(result: Result): String {
        return gson.toJson(result)
    }

    @TypeConverter
    fun stringToResult(data: String) : Result {
        val listType = object : TypeToken<Result>() {}.type
        return gson.fromJson(data, listType)
    }
}