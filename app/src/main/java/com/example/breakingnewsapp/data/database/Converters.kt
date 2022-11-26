package com.example.breakingnewsapp.data.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.breakingnewsapp.data.models_db.SourceDao
import com.example.breakingnewsapp.data.util.JsonParser
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters (private val jsonParser: JsonParser){

    @TypeConverter
    fun fromSourceJson(json: String): SourceDao{
        return jsonParser.fromJson<SourceDao>(
            json,
            object : TypeToken<SourceDao>(){}.type
        ) ?: SourceDao("", "")
    }
    @TypeConverter
    fun toSourceJson(sourceDao: SourceDao): String{
        return jsonParser.toJson(
            sourceDao,
            object : TypeToken<SourceDao>(){}.type
        ) ?: String()
    }

}

