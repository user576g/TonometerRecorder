package com.user576.tonometerrecorder

import android.app.Application

class RecorderApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val db = RecordsDB.getDatabase(applicationContext)
        dao = db.recordDao()
    }

    companion object {
        var dao : RecordDao? = null
    }
}