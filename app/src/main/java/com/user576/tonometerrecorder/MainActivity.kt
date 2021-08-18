package com.user576.tonometerrecorder

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.user576.tonometerrecorder.databinding.MainLayoutBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = MainLayoutBinding.inflate(layoutInflater)

        with (binding.header){
            date.text = resources.getString(R.string.th_date)
            sys.text = resources.getString(R.string.th_sys)
            dia.text = resources.getString(R.string.th_dia)
            pulse.text = resources.getString(R.string.th_pulse)
        }

        setContentView(binding.root)

        RecorderApp.appContext = this.applicationContext

        binding.recyclerView.adapter = RecordAdapter(
            RecorderApp.dao.getAll()
        )
    }

    fun onBtnClick(view : View) {
        if (view.id == R.id.add_record_btn) {
            startActivity(
                Intent(this, AddRecordActivity::class.java)
            )
        }
    }
}