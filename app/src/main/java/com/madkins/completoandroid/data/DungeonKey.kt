package com.madkins.completoandroid.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class DungeonKey(var dungeon: Dungeon,
                      var keyLevel: Int,
                      var completionTime: Int,  // In seconds
                      var advanceLevel: Int,    // 0 for failure, otherwise +1, +2, or +3
                      var completionDate: Date, // Will need to divide the keys up by week eventually
                      var charOwnerId: Int)     // Id of the character who did the key
{
    @PrimaryKey(autoGenerate = true)
    var keyId: Int = 0

    override fun toString(): String {
        return "Dungeon name: ${dungeon.dungeonName}\n" +
                "Key level: $keyLevel\n" +
                "Completion time: $completionTime\n" +
                "Completion date: $completionDate\n" +
                "Advance level: $advanceLevel\n" +
                "Owner ID: $charOwnerId"
    }
}