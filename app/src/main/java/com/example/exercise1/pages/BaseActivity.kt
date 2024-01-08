package com.example.exercise1.pages

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {



    companion object {

        var darkMode: Boolean = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBaseView()
    }


    private fun initBaseView() {
        darkMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK ==Configuration.UI_MODE_NIGHT_YES
        println(darkMode)
    }

}