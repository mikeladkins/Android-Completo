package com.madkins.completoandroid.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.madkins.completoandroid.database.PlayerCharacterDatabase
import java.lang.IllegalStateException
import java.util.concurrent.Executors

private const val DATABASE_NAME = "player-character-database"

// We only want one point of access to the DB, so make this repository a singleton
// TODO: Make this synchronous so it's thread-safe
class PlayerCharacterRepository private constructor(context: Context) {

    private val database: PlayerCharacterDatabase = Room.databaseBuilder(
        context.applicationContext,
        PlayerCharacterDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val playerCharacterDao = database.playerCharacterDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getPlayerCharacters(): LiveData<List<PlayerCharacter>> {
        return playerCharacterDao.getCharacters()
    }

    fun getPlayerCharacterById(id: Int): LiveData<PlayerCharacter?> {
        return playerCharacterDao.getCharacterById(id)
    }

    fun addPlayerCharacter(playerCharacter: PlayerCharacter) {
        executor.execute {
            playerCharacterDao.addCharacter(playerCharacter)
        }
    }

    fun updatePlayerCharacter(playerCharacter: PlayerCharacter) {
        executor.execute {
            playerCharacterDao.updateCharacter(playerCharacter)
        }
    }

    // Methods to initialize and get the PlayerCharacterRepository
    companion object {
        private var INSTANCE: PlayerCharacterRepository? = null

        fun initialize(context: Context) {
            if(INSTANCE == null) {
                INSTANCE = PlayerCharacterRepository(context)
            }
        }

        fun get(): PlayerCharacterRepository {
            return INSTANCE ?:
            throw IllegalStateException("PlayerCharacterRepository must be initialized!")
        }
    }
}