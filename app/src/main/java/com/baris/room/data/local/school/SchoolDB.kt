package com.baris.room.data.local.school

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.baris.room.data.local.school.dao.SchoolDao
import com.baris.room.data.local.school.entity.School

/***
 * Created by @Barış Keser on 13.10.2022.
 */

@Database(
    entities = [
        School::class
    ],
    version = 1
)
abstract class SchoolDB : RoomDatabase() {

    abstract val schoolDao: SchoolDao

    companion object {
        private var INSTANCE: SchoolDB? = null

        fun getInstance(context: Context): SchoolDB {
            return INSTANCE ?: Room.databaseBuilder(
                context,
                SchoolDB::class.java,
                "school_db"
            ).allowMainThreadQueries()
                .build().also {
                    INSTANCE = it
                }
        }
    }

}