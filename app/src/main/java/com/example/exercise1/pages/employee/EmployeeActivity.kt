package com.example.exercise1.pages.employee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exercise1.R
import com.example.exercise1.data.local.entity.EmployeeEntity
import com.example.exercise1.databinding.ActivityEmployeeBinding
import com.example.exercise1.pages.BaseActivity
import com.example.exercise1.pages.salary.SalaryActivity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeActivity : BaseActivity() {


    private val viewModel by viewModels<EmployeeViewModel>()
    lateinit var binding: ActivityEmployeeBinding
    lateinit var adapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        bindViewModel()
        bindViewEvents(savedInstanceState)

    }

    fun initView()  {
        if(darkMode){
            binding.employeeLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.background_dark))
        } else {
            binding.employeeLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.background_light))
        }
        adapter = EmployeeAdapter(object: EmployeeAdapter.EventListener{
            override fun onClickItem(item: EmployeeEntity) {
                val intent = Intent(this@EmployeeActivity, SalaryActivity::class.java)
                intent.putExtra("employee", Gson().toJson(item))
                startActivity(intent)
            }

        })
        viewModel.getAllEmployee()

    }

    fun bindViewModel() {
        viewModel.listEmployee.observe(this){
            adapter.updateEmployees(it)
            binding.rvEmployees.apply {
                adapter = this@EmployeeActivity.adapter
                layoutManager = LinearLayoutManager(this@EmployeeActivity, LinearLayoutManager.VERTICAL, false)
                itemAnimator = null
            }
        }


        viewModel.successCreate.observe(this){
            println("SUCCESSSSSSS")
            if(it){
                viewModel.getAllEmployee()
            }
        }

    }


    fun bindViewEvents(savedInstanceState: Bundle?) {
        binding.btnCreateEmpl.setOnClickListener {
            if(savedInstanceState == null) {
                val newEmployee = NewEmployeeFragment()
                supportFragmentManager.beginTransaction()
                    .add(R.id.container, newEmployee, "NewEmployee")
                    .commit()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK){
            viewModel.getAllEmployee()
        }
    }




}