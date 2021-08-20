package com.user576.tonometerrecorder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Calendar
import java.util.Locale

internal lateinit var recordAdapter : RecordAdapter

// Calendar.Builder added in API level 26
private fun createCalendar(timeStamp : Long) : Calendar =
    if (android.os.Build.VERSION_CODES.O <= android.os.Build.VERSION.SDK_INT) {
        Calendar.Builder().setInstant(timeStamp).build()
    } else {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeStamp
        calendar
    }

class RecordAdapter(records : List<Record>) : RecyclerView.Adapter<RecordAdapter.RecordViewHolder>() {

    private val _records = ArrayList(records)

    class RecordViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val tvDate : TextView = view.findViewById(R.id.date)
        val tvSys : TextView = view.findViewById(R.id.sys)
        val tvDia : TextView = view.findViewById(R.id.dia)
        val tvPulse : TextView = view.findViewById(R.id.pulse)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecordViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item, viewGroup, false)

        if (viewType == 1) {
            listOf(R.id.date, R.id.sys, R.id.dia, R.id.pulse).forEach {
                view.findViewById<TextView>(it).setBackgroundResource(R.drawable.border_green)
            }
        }

        return RecordViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {

        val record = _records[position]

        val cal = createCalendar(record.timeMillis)
        val month = cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale("ru"))
        val day = cal.get(Calendar.DAY_OF_MONTH)

        with(holder) {
            tvDate.text = String.format("%d %s", day, month)
            tvSys.text = record.sys.toString()
            tvDia.text = record.dia.toString()
            tvPulse.text = record.pulse.toString()
        }
    }

    override fun getItemCount(): Int = _records.size

    fun addRecord(record: Record) {
        _records.add(record)
        notifyItemInserted(_records.size - 1)
    }

    override fun getItemViewType(position: Int): Int = position % 2
}