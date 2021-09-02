package com.user576.tonometerrecorder

import android.content.Context
import androidx.room.*

@Dao
interface RecordDao {
    @Query("SELECT * FROM record")
    fun getAll() : List<Record>

    @Insert
    fun insert(record: Record)
}

@Database(entities = [Record::class], version = 1, exportSchema = false)
abstract class RecordsDB : RoomDatabase() {
    abstract fun recordDao(): RecordDao


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: RecordsDB? = null

        fun getDatabase(context: Context): RecordsDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecordsDB::class.java,
                    "tonometer-records"
                ).build()

                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
