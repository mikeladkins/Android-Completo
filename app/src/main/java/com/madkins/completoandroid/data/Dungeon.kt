package com.madkins.completoandroid.data

import com.madkins.completoandroid.R

enum class Dungeon(val dungeonName: String, val maxTime: Int, val iconId: Int) {                                           // maxTime is stored in seconds
    DE_OTHER_SIDE("De Other Side", 2580, R.drawable.dungeon_de_other_side),                          // 43:00
    HALLS_OF_ATONEMENT("Halls of Atonement", 1860, R.drawable.dungeon_halls_of_atonement),           // 31:00
    MISTS_OF_TIRNA_SCITHE("Mists of Tirna Scithe", 1800, R.drawable.dungeon_mists_of_tirna_scithe),  // 30:00
    NECROTIC_WAKE("Necrotic Wake", 2160, R.drawable.dungeon_necrotic_wake),                          // 36:00
    PLAGUEFALL("Plaguefall", 2280, R.drawable.dungeon_plaguefall),                                   // 38:00
    SANGUINE_DEPTHS("Sanguine Depths", 2460, R.drawable.dungeon_sanguine_depths),                    // 41:00
    SPIRES_OF_ASCENSION("Spires of Ascension", 2340, R.drawable.dungeon_spires_of_ascension),        // 39:00
    THEATRE_OF_PAIN("Theatre of Pain", 2220, R.drawable.dungeon_theatre_of_pain);                    // 37:00

    companion object {
        // for use in type converters
        fun mapDungeonFromString(dungeonName: String): Dungeon {
            return when(dungeonName) {
                "De Other Side" -> DE_OTHER_SIDE
                "Halls of Atonement" -> HALLS_OF_ATONEMENT
                "Mists of Tirna Scithe" -> MISTS_OF_TIRNA_SCITHE
                "Necrotic Wake" -> NECROTIC_WAKE
                "Plaguefall" -> PLAGUEFALL
                "Sanguine Depths" -> SANGUINE_DEPTHS
                "Spires of Ascension" -> SPIRES_OF_ASCENSION
                "Theatre of Pain" -> THEATRE_OF_PAIN
                else -> DE_OTHER_SIDE
            }
        }
    }
}