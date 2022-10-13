package com.baris.room.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.baris.room.data.local.school.SchoolDB
import com.baris.room.data.local.school.dao.SchoolDao
import com.baris.room.data.local.school.entity.School
import com.baris.room.databinding.ActivityMainBinding
import com.baris.room.ui.adapter.SchoolAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var schoolDao: SchoolDao
    private lateinit var schoolAdapter: SchoolAdapter
    private var selectedId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val schoolDB = SchoolDB.getInstance(this)
        schoolDao = schoolDB.schoolDao
        setRecyclerView()
        initListener()
    }

    private fun initListener() {
        with(binding) {
            btnAdd.setOnClickListener {
                schoolDao.insert(
                    School(
                        name = etSchoolName.text.toString(),
                        country = etCountry.text.toString(),
                        city = etCity.text.toString()
                    )
                )
                updateSchoolList()
            }
            btnUpdate.setOnClickListener {
                selectedId?.let {
                    schoolDao.update(
                        School(
                            schoolId = it,
                            name = etSchoolName.text.toString(),
                            country = etCountry.text.toString(),
                            city = etCity.text.toString()
                        )
                    )
                    updateSchoolList()
                }
            }
            btnDelete.setOnClickListener {
                selectedId?.let {
                    schoolDao.delete(
                        School(
                            schoolId = it,
                            name = etSchoolName.text.toString(),
                            country = etCountry.text.toString(),
                            city = etCity.text.toString()
                        )
                    )
                    updateSchoolList()
                }
            }
        }
    }

    private fun setRecyclerView() {
        schoolAdapter = SchoolAdapter(
            onClick = {
                onSchoolClick(it)
            }
        )
        binding.schoolList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = schoolAdapter
        }
        schoolAdapter.updateList(
            schoolDao.getAll()
        )
    }

    private fun updateSchoolList() = schoolAdapter.updateList(schoolDao.getAll())

    private fun onSchoolClick(id: Int) {
        selectedId = id
        binding.apply {
            val school = schoolDao.getById(id)
            with(school) {
                etSchoolName.setText(name)
                etCountry.setText(country)
                etCity.setText(city)
            }
        }
    }
}