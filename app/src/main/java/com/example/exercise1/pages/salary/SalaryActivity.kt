package com.example.exercise1.pages.salary

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exercise1.R
import com.example.exercise1.data.local.entity.EmployeeEntity
import com.example.exercise1.databinding.ActivitySalaryBinding
import com.example.exercise1.pages.BaseActivity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SalaryActivity : AppCompatActivity(){


    val salaryViewModel: SalaryViewModel by viewModels()

    private lateinit var salaryAdapter: SalaryAdapter
    private lateinit var binding: ActivitySalaryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySalaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        bindViewModel()
        bindViewEvents(savedInstanceState)
    }


    private fun initView() {
        salaryAdapter = SalaryAdapter()

        salaryViewModel.onViewLoaded(Gson().fromJson(intent.getStringExtra("employee"), EmployeeEntity::class.java))
        salaryViewModel.getAllSalary()

    }

    private fun bindViewModel() {

        salaryViewModel.employeeData.observe(this){
            binding.tvName.text = it.name
            binding.tvEmail.text = it.email
            binding.tvId.text = it.employeeId.toString()
            println("called this and the email ${it.email}")
        }

        salaryViewModel.listSalary.observe(this){
            if (it.isEmpty()){
                binding.emptyData.visibility = View.VISIBLE
            } else{
                binding.emptyData.visibility = View.GONE
                salaryAdapter.updateSalary(it)
                binding.rvSalary.apply {
                    adapter = salaryAdapter
                    layoutManager = LinearLayoutManager(this@SalaryActivity, LinearLayoutManager.VERTICAL, false)
                    itemAnimator = null
                }
            }
        }

        salaryViewModel.loadingData.observe(this){
            if(it){
                binding.pbSalary.visibility = View.VISIBLE
            } else
                binding.pbSalary.visibility = View.GONE
        }

        salaryViewModel.successAddSalary.observe(this){
            if(it){
                salaryViewModel.getAllSalary()
            }
        }

    }


    private fun bindViewEvents(savedInstanceState: Bundle?) {

        binding.btnCreateSalary.setOnClickListener {
            if(savedInstanceState == null) {
                val salaryFragment = NewSalaryFragment()
                salaryFragment.show(supportFragmentManager,"newsalaryfragment")
            }
        }

    }

}