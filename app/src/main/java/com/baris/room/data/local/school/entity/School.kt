package com.baris.room.data.local.school.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/***
 * Created by @Barış Keser on 13.10.2022.
 */

@Entity(
    tableName = "SCHOOL"
)
data class School(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "school_id") val schoolId: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "city") val city: String
)
