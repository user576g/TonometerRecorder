package com.user576.tonometerrecorder

import android.R.layout
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment


class MyListFragment : ListFragment() {
    companion object {
        lateinit var adapter: ArrayAdapter<String>
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val records = RecorderApp.dao.getAll()

        adapter = ArrayAdapter(
            requireContext(),
            layout.simple_list_item_1,
            records.map { it.toString() }
        )

        listAdapter = adapter
    }
}