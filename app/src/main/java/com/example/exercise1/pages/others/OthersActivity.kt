package com.example.exercise1.pages.others

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.exercise1.R
import com.example.exercise1.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OthersActivity : AppCompatActivity(), Others.View {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var presenter: Others.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

    }


    fun initView() {
        presenter.attachView(this)
        presenter.loadRandomImage()
    }

    override fun showLoading(value: Boolean) {
        if (value)
            binding.pb.visibility = View.VISIBLE
        else
            binding.pb.visibility = View.GONE
    }

    override fun showData(value: String) {
        binding.imgDog.visibility = View.VISIBLE
        Glide.with(this)
            .load(value)
            .into(binding.imgDog)
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(this, "Failed Load Image", Toast.LENGTH_SHORT).show()
    }
}