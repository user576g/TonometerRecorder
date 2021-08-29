package com.user576.tonometerrecorder

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.user576.tonometerrecorder.databinding.MainLayoutBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainLayoutBinding.inflate(layoutInflater)

        with (binding.header){
            date.text = resources.getString(R.string.th_date)
            sys.text = resources.getString(R.string.th_sys)
            dia.text = resources.getString(R.string.th_dia)
            pulse.text = resources.getString(R.string.th_pulse)
        }

        setContentView(binding.root)

        RecorderApp.appContext = this.applicationContext

        recordAdapter = RecordAdapter(
            RecorderApp.dao.getAll()
        )
        binding.recyclerView.adapter = recordAdapter
    }

    fun onBtnClick(view : View) {
        if (view.id == R.id.add_record_btn) {
            val intent = Intent(this, AddRecordActivity::class.java)
            val activityOptions =  ActivityOptions.makeSceneTransitionAnimation(
                this, Pair(binding.addRecordBtn, getString(R.string.common_view))
            )
            startActivity(intent, activityOptions.toBundle())
        }
    }
}