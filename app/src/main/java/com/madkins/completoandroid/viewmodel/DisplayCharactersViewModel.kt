package com.madkins.completoandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.madkins.completoandroid.data.PlayerCharacterRepository

class DisplayCharactersViewModel: ViewModel() {
    private val playerCharacterRepo = PlayerCharacterRepository.get()
    val playerCharactersLiveData = playerCharacterRepo.getPlayerCharacters()
}