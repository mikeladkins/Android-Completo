package com.madkins.completoandroid.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.madkins.completoandroid.R
import com.madkins.completoandroid.data.PlayerCharacter
import com.madkins.completoandroid.viewmodel.MainViewModel

class CharactersDisplayFragment: Fragment() {
    interface Callbacks {
        fun onCharacterSelected(playerCharacter: PlayerCharacter)
    }

    private var callbacks: Callbacks? = null
    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var displayCharacterRecyclerView: RecyclerView
    private var adapter: DisplayCharacterAdapter? = DisplayCharacterAdapter(emptyList())

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_characters, container, false)

        displayCharacterRecyclerView = view.findViewById(R.id.player_character_recycler_view)
        displayCharacterRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        displayCharacterRecyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.playerCharactersLiveData.observe(
            viewLifecycleOwner,
            { characters ->
                characters?.let {
                    updateUi(characters)
                }
            }
        )
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun updateUi(playerCharacters: List<PlayerCharacter>) {
        adapter = DisplayCharacterAdapter(playerCharacters)
        displayCharacterRecyclerView.adapter = adapter
    }

    // Inner Classes
    private inner class DisplayCharacterHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val nameTextView: TextView = itemView.findViewById(R.id.list_item_text_player_name)
        val levelTextView: TextView = itemView.findViewById(R.id.list_item_text_player_level)
        val classIcon: ImageView = itemView.findViewById(R.id.list_item_image_class_icon)
        val raceIcon: ImageView = itemView.findViewById(R.id.list_item_image_race_icon)
        private lateinit var playerCharacter: PlayerCharacter

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(playerCharacter: PlayerCharacter) {
            this.playerCharacter = playerCharacter
            nameTextView.text = playerCharacter.charName.replaceFirstChar { it.uppercase() }
            nameTextView.setTextColor(getColor(requireContext(), playerCharacter.charClass.colorId))
            levelTextView.text = playerCharacter.charLevel.toString()
            classIcon.setImageResource(resources.getIdentifier(mapSpecResource(playerCharacter), "drawable", requireActivity().packageName))
            raceIcon.setImageResource(resources.getIdentifier(mapRaceResource(playerCharacter), "drawable", requireActivity().packageName))
        }

        fun mapSpecResource(character: PlayerCharacter): String {
            val formattedClass = character.charClass.className.replace("\\s".toRegex(), "").lowercase()
            val formattedSpec = (character.charSpec.replace("\\s".toRegex(), "")).lowercase()
            return "spec_${formattedClass}_${formattedSpec}"
        }

        fun mapRaceResource(character: PlayerCharacter): String {
            val raceMinusWhiteSpace = character.charRace.replace("\\s".toRegex(), "")
            // Gets rid of the ' in Mag'har Orcs
            val formattedRace = raceMinusWhiteSpace.replace("'", "").lowercase()
            return "race_${formattedRace}_${character.charGender}"
        }

        override fun onClick(v: View?) {
            callbacks?.onCharacterSelected(playerCharacter)
        }
    }

    private inner class DisplayCharacterAdapter(var playerCharacters: List<PlayerCharacter>): RecyclerView.Adapter<DisplayCharacterHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplayCharacterHolder {
            val view = layoutInflater.inflate(R.layout.list_item_player_character, parent, false)
            return DisplayCharacterHolder(view)
        }

        override fun onBindViewHolder(holder: DisplayCharacterHolder, position: Int) {
            val playerCharacter = playerCharacters[position]
            holder.bind(playerCharacter)
        }

        override fun getItemCount(): Int = playerCharacters.size
    }
}