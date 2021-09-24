package com.madkins.completoandroid.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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
import java.util.*
import java.util.concurrent.Executors

class KeysFragment: Fragment() {
    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var displayKeysRecyclerView: RecyclerView
    private var adapter: DisplayKeysAdapter? = DisplayKeysAdapter(emptyList())
    private lateinit var keyFab: FloatingActionButton

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

    private fun updateUi(keys: List<DungeonKey>) {
        adapter = DisplayKeysAdapter(keys)
        displayKeysRecyclerView.adapter = adapter
    }

    private inner class DisplayKeysHolder(view: View): RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = itemView.findViewById(R.id.list_item_key_name)
        val dateTextView: TextView = itemView.findViewById(R.id.list_item_key_date)
        private lateinit var key: DungeonKey

        fun bind(key: DungeonKey) {
            this.key = key
            nameTextView.text = key.dungeon.dungeonName
            dateTextView.text = key.completionDate.toString()
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