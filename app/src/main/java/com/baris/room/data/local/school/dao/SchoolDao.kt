package com.baris.room.data.local.school.dao

import androidx.room.*
import com.baris.room.data.local.school.entity.School

/***
 * Created by @Barış Keser on 13.10.2022.
 */

@Dao
interface SchoolDao {

    @Query("select * from SCHOOL")
    fun getAll() : List<School>

    @Query("select * from SCHOOL where school_id = :id")
    fun getById(id: Int) : School

    @Insert
    fun insert(school: School)

    @Delete
    fun delete(school: School)

    @Update
    fun update(school: School)

}