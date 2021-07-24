package com.user576.tonometerrecorder

import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.setPadding
import java.util.Calendar
import java.util.Locale

private fun TableLayout.createTextViewWithText(text : CharSequence) : TextView {
    val textView = TextView(this.context)
    textView.setPadding(
        this.resources.getDimension(R.dimen.cell_padding).toInt()
    )
    textView.textAlignment = View.TEXT_ALIGNMENT_CENTER

    textView.text = text

    return textView
}

private var rowsNumber = 1

fun TableLayout.addRecord(record: Record) {

    val row = TableRow(this.context)
    row.setBackgroundResource(R.drawable.border)

    val cal = Calendar.Builder().setInstant(record.timeMillis).build()
    val month = cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale("ru"))
    val day = cal.get(Calendar.DAY_OF_MONTH)

    val labels : List<String> = with(record) {
        listOf("$day $month", sys.toString(), dia.toString(), pulse.toString())
    }

    for (j in 0 .. labels.size - 2) {
        val textView = createTextViewWithText(labels[j])
        textView.setBackgroundResource(R.drawable.border)

        row.addView(textView, j)
    }

    // last cell in row without background
    row.addView(createTextViewWithText(labels.last()), labels.size - 1)

    this.addView(row, rowsNumber)
    ++rowsNumber
}

fun TableLayout.setRecords(records : List<Record>) {

    if (records.isEmpty()) {
        this.addView(
            this.createTextViewWithText(
                resources.getText(R.string.message)
            )
        )
    } else {
        for (record in records) {
            this.addRecord(record)
        }
    }
}

fun resetRowsNumber() {
    rowsNumber = 1
}