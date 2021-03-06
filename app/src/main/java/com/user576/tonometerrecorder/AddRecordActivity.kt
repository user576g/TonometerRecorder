package com.user576.tonometerrecorder

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.user576.tonometerrecorder.databinding.AddLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddRecordActivity : AppCompatActivity() {

    private lateinit var binding : AddLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    @Inject
    lateinit var saver : SaveRecordUseCase

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
            saver.save(record)
            finish()
        } else {
            AlertDialog.Builder(view.context)
                .setMessage("Значение $result не корректно")
                .show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if (item.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
}