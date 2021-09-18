package com.user576.tonometerrecorder

import androidx.lifecycle.LiveData
import java.lang.IllegalStateException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SaveRecordUseCase {

    private val dao = RecorderApp.dao ?: throw IllegalStateException()

    fun save(record: Record) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(record)
        }
    }
}

class GetObservableRecordsUseCase {

    private val dao = RecorderApp.dao ?: throw IllegalStateException()

    fun get() : LiveData<List<Record>> = dao.getAll()
}