package com.madkins.completoandroid

import android.app.Application
import com.madkins.completoandroid.data.DungeonKeyRepository
import com.madkins.completoandroid.data.PlayerCharacterRepository

class CompletoApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize the singleton repositories while the application is running
        PlayerCharacterRepository.initialize(this)
        DungeonKeyRepository.initialize(this)
    }
}