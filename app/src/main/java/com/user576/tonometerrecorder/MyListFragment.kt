package com.user576.tonometerrecorder

import android.R.layout
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking


class MyListFragment : ListFragment() {
    companion object {
        lateinit var adapter: ArrayAdapter<String>
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val records = RecorderApp.dao.getAll()

        adapter = ArrayAdapter(
            requireContext(),
            layout.simple_list_item_1,
            records.map { it.toString() }
        )

        listAdapter = adapter
    }
}