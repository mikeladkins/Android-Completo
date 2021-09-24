package com.madkins.completoandroid.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.madkins.completoandroid.R

class ProfileFragment: Fragment() {
    private lateinit var createCharacterButton: Button
    private lateinit var viewCharactersButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createCharacterButton = view.findViewById(R.id.button_create_new_character)
        viewCharactersButton = view.findViewById(R.id.button_view_characters)

        createCharacterButton.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, CreateCharacterFragment())
                .addToBackStack(null)
                .commit()
        }

        viewCharactersButton.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, CharactersDisplayFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}