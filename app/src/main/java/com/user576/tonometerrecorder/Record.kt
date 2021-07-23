package com.user576.tonometerrecorder

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.NumberFormatException

@Entity
data class Record(
    @PrimaryKey val timeMillis : Long,
    val sys : Int,
    val dia : Int,
    val pulse : Int
) {
    companion object {

        const val VALID_RES = "VALID"

        fun verifyParamsForCreate(sys : String, dia : String, pulse: String) : String {
            val strArr = arrayOf(sys, dia, pulse)

            for (str in strArr) {
                try {
                    str.toUByte()
                } catch (ex : NumberFormatException) {
                    return str
                }
            }

            return VALID_RES
        }

        fun create(sys : String, dia : String, pulse: String) : Record {
            val currentTime = System.currentTimeMillis()
            return Record(currentTime, sys.toInt(), dia.toInt(), pulse.toInt())
        }
    }
}