package com.user576.tonometerrecorder

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.main_layout.table_layout
import kotlinx.android.synthetic.main.main_layout.add_record_btn

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_layout)

        add_record_btn.setOnClickListener {
            startActivity(
                Intent(this, AddRecordActivity::class.java)
            )
        }

        RecorderApp.appContext = this.applicationContext
        table_layout.setRecords(
            RecorderApp.dao.getAll()
        )
    }

    override fun onDestroy() {
        resetRowsNumber()
        super.onDestroy()
    }
}