package com.user576.tonometerrecorder

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.user576.tonometerrecorder.databinding.AddLayoutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddRecordActivity : AppCompatActivity() {

    private lateinit var binding : AddLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = AddLayoutBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    fun onBtnClick(view : View) {

        val sysStr : String
        val diaStr : String
        val pulseStr : String

        with(binding) {
            sysStr = sys.text.toString()
            diaStr = dia.text.toString()
            pulseStr = pulse.text.toString()
        }

        val result = Record.verifyParamsForCreate(sysStr, diaStr, pulseStr)
        if (result == Record.VALID_RES) {
            val record = Record.create(sysStr, diaStr, pulseStr)

            CoroutineScope(Dispatchers.IO).launch {
                RecorderApp.dao?.insert(record)
            }

            finish()
        } else {
            AlertDialog.Builder(view.context)
                .setMessage("Значение $result не корректно")
                .show()
        }
    }
}