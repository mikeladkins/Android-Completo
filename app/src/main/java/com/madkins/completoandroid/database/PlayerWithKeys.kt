package com.madkins.completoandroid.database

import androidx.room.Embedded
import androidx.room.Relation
import com.madkins.completoandroid.data.DungeonKey
import com.madkins.completoandroid.data.PlayerCharacter

// Models the one-to-many relationship between PlayerCharacter and DungeonKey
 data class PlayerWithKeys(
    @Embedded
    val playerCharacter: PlayerCharacter,
    @Relation(
     parentColumn = "charId",
     entityColumn = "charOwnerId"
    )
    val dungeonKeys: List<DungeonKey>
 )