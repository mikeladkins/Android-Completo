package com.madkins.completoandroid.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import com.madkins.completoandroid.R
import com.madkins.completoandroid.data.PlayerCharacter
import com.madkins.completoandroid.viewmodel.MainViewModel

class CharacterDetailsFragment: Fragment() {
    private val mainViewModel by activityViewModels<MainViewModel>()
    private var selectedCharacter: LiveData<PlayerCharacter>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}