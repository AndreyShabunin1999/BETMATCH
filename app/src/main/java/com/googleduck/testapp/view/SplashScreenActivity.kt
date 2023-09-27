package com.googleduck.testapp.view

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.googleduck.testapp.R
import com.googleduck.testapp.databinding.ActivityMainBinding
import com.googleduck.testapp.databinding.ActivitySplashSceenBinding

@Suppress("DEPRECATION")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashSceenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashSceenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val sharedPref: SharedPreferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE)

        changeTheme(sharedPref.getBoolean("THEME", false))
        changeBackgroundImage(sharedPref.getInt("IMAGE", R.drawable.background1))

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }

    private fun changeTheme(checked: Boolean) {
        if(checked){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun changeBackgroundImage(image: Int) {
        when(image) {
            R.drawable.background1 -> binding.constraintSplash.background = ContextCompat.getDrawable(this@SplashScreenActivity, R.drawable.background1)
            R.drawable.background2 -> binding.constraintSplash.background = ContextCompat.getDrawable(this@SplashScreenActivity, R.drawable.background2)
            R.drawable.background3 -> binding.constraintSplash.background = ContextCompat.getDrawable(this@SplashScreenActivity, R.drawable.background3)
        }
    }


}