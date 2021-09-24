package com.madkins.completoandroid.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.madkins.completoandroid.data.DungeonKey

@Dao
interface DungeonKeyDao {
    @Query("SELECT * FROM dungeonkey")
    fun getDungeonKeys(): LiveData<List<DungeonKey>>

    @Query("SELECT * FROM dungeonkey WHERE charOwnerId = :charId")
    fun getDungeonKeysForCharId(charId: Int?): LiveData<List<DungeonKey>>

    @Insert
    fun addDungeonKey(dungeonKey: DungeonKey)
}