package com.googleduck.testapp.view

import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.googleduck.testapp.R
import com.googleduck.testapp.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val sharedPref: SharedPreferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE)
        binding.switchChangeTheme.isChecked = sharedPref.getBoolean("THEME", false)
        binding.switchChangeNotify.isChecked = sharedPref.getBoolean("NOTIFY", false)

        changeTheme(sharedPref.getBoolean("THEME", false))
        changeBackgroundImage(sharedPref.getInt("IMAGE", R.drawable.background1))

        binding.switchChangeTheme.setOnClickListener{
            sharedPref.edit().putBoolean("THEME", binding.switchChangeTheme.isChecked).apply()
            changeTheme(binding.switchChangeTheme.isChecked)
        }

        binding.switchChangeNotify.setOnClickListener{
            sharedPref.edit().putBoolean("NOTIFY", binding.switchChangeNotify.isChecked).apply()
        }

        binding.changeBtn.setOnClickListener {
            var random: Int = randomBackgroundImage()
            sharedPref.edit().putInt("IMAGE", random).apply()
            binding.constraintSettings.background = ContextCompat.getDrawable(this@SettingsActivity, random)
        }

    }

    private fun changeTheme(checked: Boolean) {
        if(checked){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun randomBackgroundImage(): Int {
        val values: List<Int> = listOf(R.drawable.background1, R.drawable.background2, R.drawable.background3)
        return values.random()
    }

    private fun changeBackgroundImage(image: Int) {
        when(image) {
            R.drawable.background1 -> binding.constraintSettings.background = ContextCompat.getDrawable(this@SettingsActivity, R.drawable.background1)
            R.drawable.background2 -> binding.constraintSettings.background = ContextCompat.getDrawable(this@SettingsActivity, R.drawable.background2)
            R.drawable.background3 -> binding.constraintSettings.background = ContextCompat.getDrawable(this@SettingsActivity, R.drawable.background3)
        }
    }

}