package com.user576.tonometerrecorder

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.user576.tonometerrecorder.databinding.RecordsFragmentBinding

class RecordsFragment : Fragment() {

    private var binding: RecordsFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val nonNullBinding = RecordsFragmentBinding.inflate(inflater, container, false)
        binding = nonNullBinding

        with (nonNullBinding.header){
            date.text = resources.getString(R.string.th_date)
            sys.text = resources.getString(R.string.th_sys)
            dia.text = resources.getString(R.string.th_dia)
            pulse.text = resources.getString(R.string.th_pulse)
        }
        nonNullBinding.addRecordBtn.setOnClickListener{
            onAddRecordBtnClick()
        }

        return nonNullBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model : MainViewModel by viewModels()
        model.records.observe(this) { records ->
            binding?.recyclerView?.adapter = RecordAdapter(records)
        }
    }

    private fun onAddRecordBtnClick() {

        val intent = Intent(activity, AddRecordActivity::class.java)
        val activityOptions =  ActivityOptions.makeSceneTransitionAnimation(
            activity, Pair(binding?.addRecordBtn, getString(R.string.common_view))
        )
        startActivity(intent, activityOptions.toBundle())
    }
}