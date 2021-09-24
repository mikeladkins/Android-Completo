package com.madkins.completoandroid.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.madkins.completoandroid.R
import com.madkins.completoandroid.data.PlayerCharacter
import com.madkins.completoandroid.data.PlayerCharacterClass
import com.madkins.completoandroid.viewmodel.CreateCharacterViewModel
import com.madkins.completoandroid.viewmodel.MainViewModel

class CreateCharacterFragment: Fragment() {
    private lateinit var charNameEditText: EditText
    private lateinit var charLevelEditText: EditText
    private lateinit var charRaceSpinner: Spinner
    private lateinit var charClassSpinner: Spinner
    private lateinit var charSpecSpinner: Spinner
    private lateinit var charGenderSpinner: Spinner
    private lateinit var createCharacterButton: Button
    private lateinit var activeSpecs: List<String>
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // DK specs by default
        activeSpecs = getValidSpecsForClass(PlayerCharacterClass.DEATH_KNIGHT)

        charNameEditText = view.findViewById(R.id.text_character_name)
        charLevelEditText = view.findViewById(R.id.text_character_level)
        charRaceSpinner = view.findViewById(R.id.spinner_character_race)
        charClassSpinner = view.findViewById(R.id.spinner_character_class)
        charSpecSpinner = view.findViewById(R.id.spinner_character_spec)
        charGenderSpinner = view.findViewById(R.id.spinner_character_gender)
        createCharacterButton = view.findViewById(R.id.button_create_character)

        // Spinner Adapters
        val raceSpinnerAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, resources.getStringArray(R.array.character_races))
        charRaceSpinner.adapter = raceSpinnerAdapter

        val genderSpinnerAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, resources.getStringArray(R.array.character_genders))
        charGenderSpinner.adapter = genderSpinnerAdapter

        val specSpinnerAdapter = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item)
        specSpinnerAdapter.addAll(activeSpecs)
        charSpecSpinner.adapter = specSpinnerAdapter

        val classSpinnerAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, resources.getStringArray(R.array.character_classes))
        charClassSpinner.adapter = classSpinnerAdapter
        charClassSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (parent != null) {
                    activeSpecs = getValidSpecsForClass(
                        PlayerCharacterClass.mapClassFromString(
                            parent.getItemAtPosition(position).toString()
                        )
                    )

                    // Update the spinner adapter with the valid specs for the selected class
                    specSpinnerAdapter.clear()
                    specSpinnerAdapter.addAll(activeSpecs)
                    specSpinnerAdapter.notifyDataSetChanged()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Intentionally blank for now
            }
        }

        createCharacterButton.setOnClickListener {
            if(isDataValid()) {
                viewModel.saveCharacter(
                    PlayerCharacter(
                        PlayerCharacterClass.mapClassFromString(charClassSpinner.selectedItem.toString()),
                        Integer.parseInt(charLevelEditText.text.toString()),
                        charNameEditText.text.toString(),
                        charRaceSpinner.selectedItem.toString(),
                        charSpecSpinner.selectedItem.toString(),
                        charGenderSpinner.selectedItemPosition
                    )
                )
                Toast.makeText(requireActivity(), "Character created successfully!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // I think this should be moved somewhere else, but not sure where yet
    private fun getValidSpecsForClass(currentClass: PlayerCharacterClass) : List<String> {
        return when (currentClass) {
            PlayerCharacterClass.DEATH_KNIGHT -> resources.getStringArray(PlayerCharacterClass.DEATH_KNIGHT.specsId).toList()
            PlayerCharacterClass.DEMON_HUNTER -> resources.getStringArray(PlayerCharacterClass.DEMON_HUNTER.specsId).toList()
            PlayerCharacterClass.DRUID -> resources.getStringArray(PlayerCharacterClass.DRUID.specsId).toList()
            PlayerCharacterClass.HUNTER -> resources.getStringArray(PlayerCharacterClass.HUNTER.specsId).toList()
            PlayerCharacterClass.MAGE -> resources.getStringArray(PlayerCharacterClass.MAGE.specsId).toList()
            PlayerCharacterClass.MONK -> resources.getStringArray(PlayerCharacterClass.MONK.specsId).toList()
            PlayerCharacterClass.PALADIN -> resources.getStringArray(PlayerCharacterClass.PALADIN.specsId).toList()
            PlayerCharacterClass.PRIEST -> resources.getStringArray(PlayerCharacterClass.PRIEST.specsId).toList()
            PlayerCharacterClass.ROGUE -> resources.getStringArray(PlayerCharacterClass.ROGUE.specsId).toList()
            PlayerCharacterClass.SHAMAN-> resources.getStringArray(PlayerCharacterClass.SHAMAN.specsId).toList()
            PlayerCharacterClass.WARLOCK -> resources.getStringArray(PlayerCharacterClass.WARLOCK.specsId).toList()
            PlayerCharacterClass.WARRIOR -> resources.getStringArray(PlayerCharacterClass.WARRIOR.specsId).toList()
        }
    }

    private fun isDataValid(): Boolean {
        // Check for empty fields and level is within 1-60 range
        return if (charNameEditText.text.toString().trim() == "") {
            Toast.makeText(requireActivity(), "Enter a character name", Toast.LENGTH_SHORT).show()
            false
        } else if (charLevelEditText.text.toString().trim() == "") {
            Toast.makeText(requireActivity(), "Enter a character level", Toast.LENGTH_SHORT).show()
            false
        } else if (Integer.parseInt(charLevelEditText.text.toString()) > 60 || Integer.parseInt(charLevelEditText.text.toString()) < 1) {
            Toast.makeText(requireActivity(), "Character level must be between 1 and 60", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }
}