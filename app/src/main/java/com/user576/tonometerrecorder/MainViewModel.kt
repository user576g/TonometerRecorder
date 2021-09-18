package com.user576.tonometerrecorder

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import java.lang.IllegalStateException

// View model for MainActivity
class MainViewModel : ViewModel() {
    val records : LiveData<List<Record>> by lazy {
        RecorderApp.dao?.getAll() ?: throw IllegalStateException()
    }
}