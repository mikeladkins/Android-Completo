package com.madkins.completoandroid.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.madkins.completoandroid.database.PlayerCharacterDatabase
import java.lang.IllegalStateException
import java.util.concurrent.Executors

private const val DATABASE_NAME = "player-character-database"

class DungeonKeyRepository private constructor(context: Context) {
    private val database: PlayerCharacterDatabase = Room.databaseBuilder(
        context.applicationContext,
        PlayerCharacterDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val dungeonKeyDao = database.dungeonKeyDao()
    private val playerWithKeysDao = database.playerWithKeysDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getDungeonKeysForCharacter(charId: Int?): List<DungeonKey> {
       return playerWithKeysDao.getKeysForPlayerId(charId).dungeonKeys
    }

    fun getDungeonKeysLiveData(): LiveData<List<DungeonKey>> {
        return dungeonKeyDao.getDungeonKeys()
    }

    fun getDungeonKeysLiveDataForCharacter(charId: Int?): LiveData<List<DungeonKey>> {
        return dungeonKeyDao.getDungeonKeysForCharId(charId)
    }

    fun addDungeonKey(dungeonKey: DungeonKey) {
        executor.execute {
            dungeonKeyDao.addDungeonKey(dungeonKey)
        }
    }

    companion object {
        private var INSTANCE: DungeonKeyRepository? = null

        fun initialize(context: Context) {
            if(INSTANCE == null) {
                INSTANCE = DungeonKeyRepository(context)
            }
        }

        fun get(): DungeonKeyRepository {
            return INSTANCE ?:
            throw IllegalStateException("DungeonKeyRepository must be initialized!")
        }
    }
}