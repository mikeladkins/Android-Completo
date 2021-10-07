package com.madkins.completoandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView.LABEL_VISIBILITY_LABELED
import com.madkins.completoandroid.data.DungeonKey
import com.madkins.completoandroid.data.PlayerCharacter
import com.madkins.completoandroid.fragment.*
import com.madkins.completoandroid.viewmodel.MainViewModel

// This Activity will host a container for a fragment, and that fragment will change depending on user input
// Will be the only activity in the app
// Don't know how many viewmodels we should have yet

class MainActivity : AppCompatActivity(), CharactersDisplayFragment.Callbacks, KeysFragment.Callbacks {
    private lateinit var bottomNavBar: BottomNavigationView
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var actionBar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Reference to the title bar so we can change the text
        actionBar = supportActionBar!!
        // Some default value for the title bar
        updateTitleBar(null)

        bottomNavBar = findViewById(R.id.bottom_navigation)
        bottomNavBar.labelVisibilityMode = LABEL_VISIBILITY_LABELED
        bottomNavBar.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.menu_item_characters -> loadFragment(CharactersDisplayFragment())
                R.id.menu_item_character_details -> loadFragment(CharacterDetailsFragment())
                R.id.menu_item_keys -> loadFragment(KeysFragment())
                //R.id.menu_item_vault_progress -> loadFragment(VaultFragment())
                else -> loadFragment(ProfileFragment())
            }
            true
        }

        mainViewModel.selectedCharacter.observe(
            this,
            { character ->
                updateTitleBar(character?.charName)
            }
        )

        // Load Characters Display Fragment by default
        loadFragment(CharactersDisplayFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun updateTitleBar(selectedCharacterName: String?) {
        val title: String = selectedCharacterName ?: "No Character Selected"
        actionBar.title = title.replaceFirstChar { it.uppercase() }
    }

    // Callbacks
    override fun onCharacterSelected(playerCharacter: PlayerCharacter) {
        mainViewModel.selectedCharacter.value = playerCharacter
        println("---Character Selected---\n$playerCharacter")
    }

    override fun onDungeonSelected(key: DungeonKey) {
        println("---Dungeon Selected---\n$key")
        loadFragment(DungeonDetailsFragment(key))
    }
}