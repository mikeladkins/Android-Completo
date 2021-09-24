package com.madkins.completoandroid.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.madkins.completoandroid.data.PlayerCharacter

@Dao
interface PlayerCharacterDao {
    @Query("SELECT * FROM playercharacter")
    fun getCharacters(): LiveData<List<PlayerCharacter>>

    @Query("SELECT * FROM playercharacter WHERE charId = :id")
    fun getCharacterById(id: Int): LiveData<PlayerCharacter?>

    @Update
    fun updateCharacter(playerCharacter: PlayerCharacter)

    @Insert
    fun addCharacter(playerCharacter: PlayerCharacter)
}