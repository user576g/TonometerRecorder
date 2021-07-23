package com.user576.tonometerrecorder

import android.content.Intent
import android.os.Bundle
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.main_layout.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_layout)

        RecorderApp.appContext = this.applicationContext

        val records = RecorderApp.dao.getAll()
        for ((i, record) in records.withIndex()) {
            val row = TableRow(this)
            row.setBackgroundResource(R.drawable.border)

            val cal = Calendar.Builder().setInstant(record.timeMillis).build()
            val month = cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale("ru"))
            val day = cal.get(Calendar.DAY_OF_MONTH)

            val labels : List<String> = with(record) {
                listOf("$day $month", sys.toString(), dia.toString(), pulse.toString())
            }

            for (j in 0 .. labels.size - 2) {
                val textView = TextView(this)
                textView.setPadding(10, 10, 10, 10)
                textView.setBackgroundResource(R.drawable.border)

                textView.text = labels[j]
                row.addView(textView, j)
            }

            // last cell in row without background
            val textView = TextView(this)
            textView.setPadding(10, 10, 10, 10)
            textView.text = labels.last()
            row.addView(textView, labels.size - 1)

            table_layout.addView(row, i + 1)
        }

        add_record_btn.setOnClickListener {
            startActivity(
                Intent(this, AddRecordActivity::class.java)
            )
        }
    }
}