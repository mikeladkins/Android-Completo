package com.madkins.completoandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.madkins.completoandroid.data.DungeonKey
import com.madkins.completoandroid.data.DungeonKeyRepository
import com.madkins.completoandroid.data.PlayerCharacter
import com.madkins.completoandroid.data.PlayerCharacterRepository

class MainViewModel: ViewModel() {
    private val charRepo = PlayerCharacterRepository.get()
    private val keyRepo = DungeonKeyRepository.get()
    val playerCharactersLiveData = charRepo.getPlayerCharacters()
    var selectedCharacter: MutableLiveData<PlayerCharacter> = MutableLiveData<PlayerCharacter>()


    fun saveCharacter(characterToSave: PlayerCharacter) {
        charRepo.addPlayerCharacter(characterToSave)
    }

    fun saveKey(keyToSave: DungeonKey) {
        keyRepo.addDungeonKey(keyToSave)
    }

    fun getKeysLiveDataForCharacter(charId: Int?): LiveData<List<DungeonKey>> {
        return keyRepo.getDungeonKeysLiveDataForCharacter(charId)
    }
}