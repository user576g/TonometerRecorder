package com.user576.tonometerrecorder

import android.content.Context

object RecorderApp {
    lateinit var appContext : Context

    private val db by lazy { RecordsDB.getDatabase(appContext) }

    val dao by lazy { db.recordDao() }
}