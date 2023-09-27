package com.googleduck.testapp.view

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.googleduck.testapp.R
import com.googleduck.testapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val sharedPref: SharedPreferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE)
        changeBackgroundImage(sharedPref.getInt("IMAGE", R.drawable.background1))

        binding.settingsBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
        }

        binding.statisticsBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, StatisticsActivity::class.java))
        }

        binding.newsBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, NewsActivity::class.java))
        }

        binding.tableBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, TablesActivity::class.java))
        }
    }

    private fun changeBackgroundImage(image: Int) {
        when(image) {
            R.drawable.background1 -> binding.mainConstraint.background = ContextCompat.getDrawable(this@MainActivity, R.drawable.background1)
            R.drawable.background2 -> binding.mainConstraint.background = ContextCompat.getDrawable(this@MainActivity, R.drawable.background2)
            R.drawable.background3 -> binding.mainConstraint.background = ContextCompat.getDrawable(this@MainActivity, R.drawable.background3)
        }
    }
}