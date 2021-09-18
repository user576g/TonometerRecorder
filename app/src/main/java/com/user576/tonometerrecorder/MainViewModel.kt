package com.user576.tonometerrecorder

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

// View model for MainActivity
class MainViewModel : ViewModel() {

    val records : LiveData<List<Record>> = GetObservableRecordsUseCase().get()
}