package com.madkins.completoandroid.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlayerCharacter(var charClass: PlayerCharacterClass,
                           var charLevel: Int,
                           var charName: String,
                           var charRace: String,
                           var charSpec: String,
                           var charGender: Int) { // 0 is male, 1 is female

    @PrimaryKey(autoGenerate = true)
    var charId: Int = 0

    override fun toString(): String {
        return "Name: $charName\n" +
                "Race: $charRace\n" +
                "Class: " + charClass.className + "\n" +
                "Spec: $charSpec\n" +
                "Level: $charLevel\n" +
                "ID: $charId\n" +
                "Gender: $charGender\n"
    }
}