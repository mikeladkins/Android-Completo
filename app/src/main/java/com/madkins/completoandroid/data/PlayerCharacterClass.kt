package com.madkins.completoandroid.data

import com.madkins.completoandroid.R

enum class PlayerCharacterClass (val className: String, val colorId: Int, val iconId: Int, val specsId: Int) {
    DEATH_KNIGHT("Death Knight", R.color.class_death_knight, R.drawable.class_death_knight, R.array.death_knight_specs),
    DEMON_HUNTER("Demon Hunter", R.color.class_demon_hunter, R.drawable.class_demon_hunter, R.array.demon_hunter_specs),
    DRUID("Druid", R.color.class_druid, R.drawable.class_druid, R.array.druid_specs),
    HUNTER("Hunter", R.color.class_hunter, R.drawable.class_hunter, R.array.hunter_specs),
    MAGE("Mage", R.color.class_mage, R.drawable.class_mage, R.array.mage_specs),
    MONK("Monk", R.color.class_monk, R.drawable.class_monk, R.array.monk_specs),
    PALADIN("Paladin", R.color.class_paladin, R.drawable.class_paladin, R.array.paladin_specs),
    PRIEST("Priest", R.color.class_priest, R.drawable.class_priest, R.array.priest_specs),
    ROGUE("Rogue", R.color.class_rogue, R.drawable.class_rogue, R.array.rogue_specs),
    SHAMAN("Shaman", R.color.class_shaman, R.drawable.class_shaman, R.array.shaman_specs),
    WARLOCK("Warlock", R.color.class_warlock, R.drawable.class_warlock, R.array.warlock_specs),
    WARRIOR("Warrior", R.color.class_warrior, R.drawable.class_warrior, R.array.warrior_specs);

    companion object {
        // for use in type converters
        fun mapClassFromString(className: String?): PlayerCharacterClass {
            return when (className) {
                "Death Knight" -> DEATH_KNIGHT
                "Demon Hunter" -> DEMON_HUNTER
                "Druid" -> DRUID
                "Hunter" -> HUNTER
                "Mage" -> MAGE
                "Monk" -> MONK
                "Paladin" -> PALADIN
                "Priest" -> PRIEST
                "Rogue" -> ROGUE
                "Shaman" -> SHAMAN
                "Warlock" -> WARLOCK
                "Warrior" -> WARRIOR
                else -> WARRIOR
            }
        }
    }
}