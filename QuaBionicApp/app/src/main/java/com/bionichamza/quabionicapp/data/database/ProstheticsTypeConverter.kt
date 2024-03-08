package com.bionichamza.quabionicapp.data.database

import androidx.room.TypeConverter
import com.bionichamza.quabionicapp.models.Prosthetics
import com.bionichamza.quabionicapp.models.ProstheticsInfo
import com.bionichamza.quabionicapp.models.ProstheticsInfoItem
import com.bionichamza.quabionicapp.models.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProstheticsTypeConverter {

    var gson = Gson()
    @TypeConverter
    fun prostheticsInfoToString(prostheticsInfo : ProstheticsInfo) : String {
        return gson.toJson(prostheticsInfo)
    }

    @TypeConverter
    fun stringToProstheticsInfo(data: String) : ProstheticsInfo {
        val listType = object : TypeToken<ProstheticsInfo>() {}.type
        return gson.fromJson(data , listType)
    }

    @TypeConverter
    fun prostheticsInfoItemToString(prostheticsInfoItem: ProstheticsInfoItem) : String {
        return gson.toJson(prostheticsInfoItem)
    }

    @TypeConverter
    fun stringToProstheticsInfoItem(data : String) : ProstheticsInfoItem {
        val listType = object : TypeToken<ProstheticsInfoItem>() {}.type
        return gson.fromJson(data , listType)
    }

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