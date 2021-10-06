package com.madkins.completoandroid.fragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.madkins.completoandroid.R
import com.madkins.completoandroid.data.Dungeon
import com.madkins.completoandroid.data.DungeonKey
import com.madkins.completoandroid.data.PlayerCharacter
import com.madkins.completoandroid.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.Executors

class KeysFragment: Fragment() {
    interface Callbacks {
        fun onDungeonSelected(dungeonKey: DungeonKey)
    }

    private var callbacks: Callbacks? = null
    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var displayKeysRecyclerView: RecyclerView
    private var adapter: DisplayKeysAdapter? = DisplayKeysAdapter(emptyList())
    private lateinit var keyFab: FloatingActionButton
    private val outputFormatter = SimpleDateFormat("MMM d", Locale.ENGLISH)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_keys, container, false)

        displayKeysRecyclerView = view.findViewById(R.id.player_keys_recycler_view)
        displayKeysRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        displayKeysRecyclerView.adapter = adapter

        keyFab = view.findViewById(R.id.fab_keys)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getKeysLiveDataForCharacter(viewModel.selectedCharacter.value?.charId).observe(
            viewLifecycleOwner
        ) { keys ->
            keys?.let {
                updateUi(keys)
            }
        }
        keyFab.setOnClickListener {
            CreateKeyDialogFragment().show(requireActivity().supportFragmentManager, null)
        }
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun updateUi(keys: List<DungeonKey>) {
        adapter = DisplayKeysAdapter(keys)
        displayKeysRecyclerView.adapter = adapter
    }


    // Inner Classes
    private inner class DisplayKeysHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val iconImageView: ImageView = itemView.findViewById(R.id.list_item_dungeon_icon)
        val levelTextView: TextView = itemView.findViewById(R.id.list_item_key_level)
        val nameTextView: TextView = itemView.findViewById(R.id.list_item_key_name)
        val dateTextView: TextView = itemView.findViewById(R.id.list_item_key_date)
        val completionTextView: TextView = itemView.findViewById(R.id.list_item_key_completion_level)
        private lateinit var key: DungeonKey

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(key: DungeonKey) {
            this.key = key
            iconImageView.setImageResource(key.dungeon.iconId)
            levelTextView.text = key.keyLevel.toString()
            nameTextView.text = key.dungeon.dungeonName
            dateTextView.text = outputFormatter.format(key.completionDate)
            completionTextView.text = "+${key.advanceLevel.toString()}"
            when(key.advanceLevel) {
                0 -> completionTextView.setTextColor(resources.getColor(R.color.complete_plus_zero))
                1 -> completionTextView.setTextColor(resources.getColor(R.color.complete_plus_one))
                2 -> completionTextView.setTextColor(resources.getColor(R.color.complete_plus_two))
                3 -> completionTextView.setTextColor(resources.getColor(R.color.complete_plus_three))
                else -> completionTextView.setTextColor(resources.getColor(R.color.complete_plus_zero))
            }
        }

        override fun onClick(v: View?) {
            callbacks?.onDungeonSelected(key)
        }
    }

    private inner class DisplayKeysAdapter(var keys: List<DungeonKey>): RecyclerView.Adapter<DisplayKeysHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplayKeysHolder {
            val view = layoutInflater.inflate(R.layout.list_item_player_key, parent, false)
            return DisplayKeysHolder(view)
        }

        override fun onBindViewHolder(holder: DisplayKeysHolder, position: Int) {
            val key = keys[position]
            holder.bind(key)
        }

        override fun getItemCount(): Int = keys.size

    }
}