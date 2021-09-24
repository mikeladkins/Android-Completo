package com.madkins.completoandroid.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.madkins.completoandroid.data.DungeonKey
import com.madkins.completoandroid.data.PlayerCharacter

@Database(entities = [PlayerCharacter::class, DungeonKey::class], version=1)
@TypeConverters(PlayerCharacterTypeConverters::class, DungeonKeyTypeConverters::class)
abstract class PlayerCharacterDatabase: RoomDatabase() {

    // Return a reference to the DAO so we can call those functions
    abstract fun playerCharacterDao(): PlayerCharacterDao
    abstract fun dungeonKeyDao(): DungeonKeyDao
    abstract fun playerWithKeysDao(): PlayerWithKeysDao
}