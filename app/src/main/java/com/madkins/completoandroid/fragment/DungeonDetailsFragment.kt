package com.madkins.completoandroid.fragment

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.madkins.completoandroid.R
import com.madkins.completoandroid.data.DungeonKey

class DungeonDetailsFragment(key: DungeonKey): Fragment() {
    private lateinit var imageDungeonIcon: ImageView
    private lateinit var textDungeonName: TextView
    private lateinit var textDungeonKeyLevel: TextView
    private lateinit var textDungeonDate: TextView
    private lateinit var textDungeonAdvanceLevel: TextView
    private val dungeonKey = key

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dungeon_details, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageDungeonIcon = view.findViewById(R.id.dungeon_details_icon)
        textDungeonName = view.findViewById(R.id.dungeon_details_name)
        textDungeonKeyLevel = view.findViewById(R.id.dungeon_details_key_level)
        textDungeonDate = view.findViewById(R.id.dungeon_details_date)

        imageDungeonIcon.setImageResource(dungeonKey.dungeon.iconId)
        textDungeonName.text = dungeonKey.dungeon.dungeonName
        textDungeonKeyLevel.text = dungeonKey.keyLevel.toString()
        textDungeonDate.text = dungeonKey.completionDate.toString()
    }
}