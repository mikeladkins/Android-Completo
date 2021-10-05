package com.madkins.completoandroid.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.datepicker.MaterialDatePicker
import com.madkins.completoandroid.R
import com.madkins.completoandroid.data.Dungeon
import com.madkins.completoandroid.data.DungeonKey
import com.madkins.completoandroid.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class CreateKeyDialogFragment: DialogFragment() {
    private lateinit var dungeonSpinner: Spinner
    private lateinit var createDungeonButton: Button
    private lateinit var pickCalendarButton: Button
    private lateinit var dateTextField: TextView
    private lateinit var keyCompletionRadioGroup: RadioGroup
    private lateinit var radioButtonDeplete: RadioButton
    private lateinit var radioButtonPlusOne: RadioButton
    private lateinit var radioButtonPlusTwo: RadioButton
    private lateinit var radioButtonPlusThree: RadioButton
    private lateinit var date: Date
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Theme_App_Dialog_FullScreen)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_create_key, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireDialog().window?.setWindowAnimations(R.style.DialogAnimation)
        var completionLevel = 0
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        createDungeonButton = view.findViewById(R.id.button_create_dungeon_key)
        pickCalendarButton = view.findViewById(R.id.button_enter_key_date)
        dateTextField = view.findViewById(R.id.text_selected_date)
        dungeonSpinner = view.findViewById(R.id.spinner_dungeon_name)
        keyCompletionRadioGroup = view.findViewById(R.id.radio_group_key_outcome)
        radioButtonDeplete = view.findViewById(R.id.radio_button_deplete)
        radioButtonPlusOne = view.findViewById(R.id.radio_button_plus_one)
        radioButtonPlusTwo = view.findViewById(R.id.radio_button_plus_two)
        radioButtonPlusThree = view.findViewById(R.id.radio_button_plus_three)

        val dungeonSpinnerAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, resources.getStringArray(R.array.dungeon_names))
        dungeonSpinner.adapter = dungeonSpinnerAdapter

        radioButtonDeplete.setOnClickListener { completionLevel = 0 }
        radioButtonPlusOne.setOnClickListener { completionLevel = 1 }
        radioButtonPlusTwo.setOnClickListener { completionLevel = 2 }
        radioButtonPlusThree.setOnClickListener { completionLevel = 3 }

        datePicker.addOnPositiveButtonClickListener {
            date = Date(it)
            dateTextField.text = date.toString()
            pickCalendarButton.text = date.toString()
        }

        pickCalendarButton.setOnClickListener {
            datePicker.show(requireActivity().supportFragmentManager, null)
        }

        createDungeonButton.setOnClickListener {
            val keyToSave = DungeonKey(
                Dungeon.mapDungeonFromString(dungeonSpinner.selectedItem.toString()),
                1,
                1,
                completionLevel,
                date,
                viewModel.selectedCharacter.value!!.charId,
            )
            viewModel.saveKey(keyToSave)
        }
    }
}