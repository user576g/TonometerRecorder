package com.user576.tonometerrecorder.di

import android.content.Context
import com.user576.tonometerrecorder.RecordDao
import com.user576.tonometerrecorder.RecordsDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    fun provideRecordDao(@ApplicationContext appContext: Context): RecordDao =
        RecordsDB.getDatabase(appContext).recordDao()
}