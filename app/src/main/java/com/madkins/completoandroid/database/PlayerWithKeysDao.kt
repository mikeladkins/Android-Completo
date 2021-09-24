package com.madkins.completoandroid.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface PlayerWithKeysDao {
    @Transaction
    @Query("SELECT * FROM playercharacter WHERE charId = :id")
    fun getKeysForPlayerId(id: Int?): PlayerWithKeys
}