package com.example.exercise1.pages.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.exercise1.R
import com.example.exercise1.databinding.ActivityMainBinding
import com.example.exercise1.pages.BaseActivity
import com.example.exercise1.pages.employee.EmployeeActivity
import com.example.exercise1.pages.others.OthersActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        bindViewModel()
        bindViewEvents()

    }



    private fun initView() {
        if(darkMode){
            binding.mainLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.background_dark))
        } else {
            binding.mainLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.background_light))
        }
        mainViewModel.getRandomDog()

    }


    private fun bindViewModel() {
        mainViewModel.loadingData.observe(this){
            if(it){
                binding.pb.visibility = View.VISIBLE
            } else{
                binding.pb.visibility = View.GONE
            }
        }

        mainViewModel.randomDogData.observe(this){
            binding.imgDog.visibility = View.VISIBLE
            Glide.with(this)
                .load(it.message)
                .into(binding.imgDog)
        }


        mainViewModel.errorData.observe(this){
            println(it)
        }


    }

    private fun bindViewEvents() {
        binding.btnNextPage.setOnClickListener {
            startActivity(Intent(this, OthersActivity::class.java))
        }

        binding.tvCheckEmployee.setOnClickListener {
            startActivity(Intent(this, EmployeeActivity::class.java))
        }
    }


}