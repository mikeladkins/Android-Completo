package com.madkins.completoandroid.database

import androidx.room.TypeConverter
import com.madkins.completoandroid.data.Dungeon
import java.util.*

class DungeonKeyTypeConverters {

    @TypeConverter
    fun fromDungeon( dungeon: Dungeon?): String? {
        return dungeon?.dungeonName
    }

    @TypeConverter
    fun toDungeon(dungeonName: String): Dungeon {
        return Dungeon.mapDungeonFromString(dungeonName)
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(time: Long?): Date? {
        return time?.let {
            Date(it)
        }
    }
}