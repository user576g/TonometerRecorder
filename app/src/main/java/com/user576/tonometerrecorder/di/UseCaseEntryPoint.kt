package com.user576.tonometerrecorder.di

import com.user576.tonometerrecorder.RecordDao
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@EntryPoint
interface UseCaseEntryPoint {

    fun recordDao() : RecordDao
}