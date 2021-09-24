package com.madkins.completoandroid.database

import androidx.room.TypeConverter
import com.madkins.completoandroid.data.PlayerCharacterClass

class PlayerCharacterTypeConverters {

    @TypeConverter
    fun fromPlayerCharacterClass(charClass: PlayerCharacterClass?): String? {
        return charClass?.className
    }

    @TypeConverter
    fun toPlayerCharacterClass(charClass: String?): PlayerCharacterClass {
        return PlayerCharacterClass.mapClassFromString(charClass)
    }
}