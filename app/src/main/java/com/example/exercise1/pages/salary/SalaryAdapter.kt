package com.example.exercise1.pages.salary

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise1.data.local.entity.SalaryEntity
import com.example.exercise1.databinding.ItemSalaryBinding

class SalaryAdapter : RecyclerView.Adapter<SalaryAdapter.ViewHolder>() {


    var listSalary = listOf<SalaryEntity>()


    @SuppressLint("NotifyDataSetChanged")
    fun updateSalary(list: List<SalaryEntity>){
        this.listSalary = list
        notifyDataSetChanged()
    }



    class ViewHolder (private val binding: ItemSalaryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : SalaryEntity) {
            binding.tvSalary.text = item.amount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSalaryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listSalary.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listSalary[position])
    }
}