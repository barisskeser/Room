package com.baris.room.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baris.room.data.local.school.entity.School
import com.baris.room.databinding.ItemSchoolBinding

/***
 * Created by @Barış Keser on 13.10.2022.
 */
class SchoolAdapter(private val onClick: (id: Int) -> Unit) :
    RecyclerView.Adapter<SchoolAdapter.ViewHolder>() {

    private val schoolList = mutableListOf<School>()

    class ViewHolder(
        private val binder: ItemSchoolBinding,
        private val onClick: (id: Int) -> Unit
    ) : RecyclerView.ViewHolder(binder.root) {
        fun bind(school: School) {
            with(school) {
                binder.apply {
                    tvSchoolName.text = "$schoolId: $name"
                    tvSchoolName.setOnClickListener {
                        onClick.invoke(school.schoolId)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemSchoolBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onClick
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(schoolList[position])
    }

    override fun getItemCount() = schoolList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<School>) {
        schoolList.clear()
        schoolList.addAll(newList)
        notifyDataSetChanged()
    }

}