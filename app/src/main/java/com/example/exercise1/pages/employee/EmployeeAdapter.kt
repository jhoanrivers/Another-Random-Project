package com.example.exercise1.pages.employee

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise1.data.local.entity.EmployeeEntity
import com.example.exercise1.databinding.ItemEmployeeBinding
import okhttp3.internal.notify

class EmployeeAdapter (val listener: EmployeeAdapter.EventListener): RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {

    var employees: List<EmployeeEntity> = listOf()


    class ViewHolder (private val binding: ItemEmployeeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: EmployeeEntity) {
            binding.tvName.text = item.name
            binding.tvEmail.text = item.email
            binding.tvId.text = item.employeeId.toString()
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(employees[position])
        holder.itemView.setOnClickListener {
            listener.onClickItem(employees[position])
        }
    }

    override fun getItemCount(): Int {
        return employees.size
    }

    fun updateEmployees(it: List<EmployeeEntity>) {
        employees = it
        notifyDataSetChanged()

    }


    interface EventListener {

        fun onClickItem(item: EmployeeEntity)

    }
}