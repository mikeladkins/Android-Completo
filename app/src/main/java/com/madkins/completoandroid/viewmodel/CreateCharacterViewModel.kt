package com.madkins.completoandroid.viewmodel

import androidx.lifecycle.ViewModel
import com.madkins.completoandroid.data.PlayerCharacter
import com.madkins.completoandroid.data.PlayerCharacterRepository

class CreateCharacterViewModel: ViewModel() {
    private val playerCharacterRepo = PlayerCharacterRepository.get()
    fun saveCharacter(characterToSave: PlayerCharacter) {
        playerCharacterRepo.addPlayerCharacter(characterToSave)
    }
}