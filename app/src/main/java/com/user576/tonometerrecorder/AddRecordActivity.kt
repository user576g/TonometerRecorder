package com.user576.tonometerrecorder

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.add_layout.add_btn
import kotlinx.android.synthetic.main.add_layout.sys
import kotlinx.android.synthetic.main.add_layout.dia
import kotlinx.android.synthetic.main.add_layout.pulse

class AddRecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.add_layout)

        add_btn.setOnClickListener {
            val sysStr : String = sys.text.toString()
            val diaStr : String = dia.text.toString()
            val pulseStr : String = pulse.text.toString()

            val result = Record.verifyParamsForCreate(sysStr, diaStr, pulseStr)
            if (result == Record.VALID_RES) {
                val record = Record.create(sysStr, diaStr, pulseStr)
                RecorderApp.dao.insert(record)
                startActivity(
                    Intent(this, MainActivity::class.java)
                )
                finish()
            } else {
                AlertDialog.Builder(it.context)
                    .setMessage("Значение $result не корректно")
                    .show()
            }
        }
    }
}