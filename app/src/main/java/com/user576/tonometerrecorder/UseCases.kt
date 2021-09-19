package com.user576.tonometerrecorder

import androidx.lifecycle.LiveData
import com.user576.tonometerrecorder.di.UseCaseEntryPoint
import dagger.hilt.android.EntryPointAccessors
import java.lang.IllegalStateException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class UseCase {

    protected val recordDao : RecordDao by lazy {
        val appContext = RecorderApp.appContext
        if (appContext == null) {
            throw IllegalStateException()
        } else {
            EntryPointAccessors.fromApplication(
                appContext, UseCaseEntryPoint::class.java)
                .recordDao()
        }
    }
}

class SaveRecordUseCase @Inject constructor() : UseCase() {

    fun save(record: Record) {
        CoroutineScope(Dispatchers.IO).launch {
            recordDao.insert(record)
        }
    }
}

class GetObservableRecordsUseCase @Inject constructor() : UseCase() {

    fun get() : LiveData<List<Record>> = recordDao.getAll()
}