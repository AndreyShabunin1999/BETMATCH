package com.googleduck.testapp.view

import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.googleduck.testapp.R
import com.googleduck.testapp.adapter.StatisticsAdapter
import com.googleduck.testapp.api.ApiInterface
import com.googleduck.testapp.api.ApiUtilities
import com.googleduck.testapp.databinding.ActivityStatisticsBinding
import com.googleduck.testapp.model.StatisticsAttackModel
import com.googleduck.testapp.model.StatisticsDefenseModel
import com.googleduck.testapp.repository.StatisticsAttackRepository
import com.googleduck.testapp.repository.StatisticsDefenseRepository
import com.googleduck.testapp.viewmodel.StatisticsAttackViewModel
import com.googleduck.testapp.viewmodel.StatisticsAttackViewModelFactory
import com.googleduck.testapp.viewmodel.StatisticsDefenseViewModel
import com.googleduck.testapp.viewmodel.StatisticsDefenseViewModelFactory

class StatisticsActivity : AppCompatActivity() {

    private lateinit var statisticsDefenseViewModel: StatisticsDefenseViewModel
    private lateinit var statisticsAttackViewModel: StatisticsAttackViewModel
    private var statDefense = ArrayList<StatisticsDefenseModel>()
    private var statAttack = ArrayList<StatisticsAttackModel>()
    private lateinit var adapter: StatisticsAdapter
    private lateinit var binding: ActivityStatisticsBinding
    private var mode: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatisticsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val sharedPref: SharedPreferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE)
        changeBackgroundImage(sharedPref.getInt("IMAGE", R.drawable.background1))

        val apiInterface = ApiUtilities.getInstance().create(ApiInterface::class.java)

        val statisticsDefenseRepository = StatisticsDefenseRepository(apiInterface)
        val statisticsAttackRepository = StatisticsAttackRepository(apiInterface)

        statisticsDefenseViewModel = ViewModelProvider(this, StatisticsDefenseViewModelFactory(statisticsDefenseRepository)).get(StatisticsDefenseViewModel::class.java)
        statisticsAttackViewModel = ViewModelProvider(this, StatisticsAttackViewModelFactory(statisticsAttackRepository)).get(StatisticsAttackViewModel::class.java)

        binding.rvStat.setHasFixedSize(true)
        binding.rvStat.layoutManager = LinearLayoutManager(this)

        statisticsDefenseViewModel.stat.observe(this) {
            for (i in it.indices) {
                statDefense.add(it[i])
            }
            updateRV(mode)
        }

        statisticsAttackViewModel.stat.observe(this) {
            println(it.toString())
            for (i in it.indices) {
                statAttack.add(it[i])
            }
            updateRV(mode)
        }

        changeNameRow(mode)

        binding.btnAttack.setOnClickListener {
            mode = 1
            changeNameRow(mode)
            adapter.setMode(mode)
            adapter.notifyDataSetChanged()
        }

        binding.btnDefense.setOnClickListener {
            mode = 0
            changeNameRow(mode)
            adapter.setMode(mode)
            adapter.notifyDataSetChanged()
        }

    }

    private fun updateRV(localMode: Int){
        if(localMode == 0){
            adapter = StatisticsAdapter(localMode, statDefense, statAttack)
            binding.rvStat.adapter = adapter
        }
    }

    private fun changeNameRow(mode: Int){
        if(mode == 0){
            val list = ArrayList<String>(listOf(*resources.getStringArray(R.array.stat_defense)))
            binding.tvOffsidesStat.visibility = View.VISIBLE
            binding.tvCommandsStat.text = list[0]
            binding.tvTournamentStat.text = list[1]
            binding.tvBlowsStat.text = list[2]
            binding.tvSelectStat.text = list[3]
            binding.tvIntercStat.text = list[4]
            binding.tvFoulsStat.text = list[5]
            binding.tvRatingStat.text = list[6]
            binding.tvOffsidesStat.text = list[7]
            binding.btnDefense.background = ContextCompat.getDrawable(this@StatisticsActivity, R.drawable.rounded_button)
            binding.btnAttack.background = ContextCompat.getDrawable(this@StatisticsActivity, R.drawable.rounded_button_not_checked)
        } else {
            val list = ArrayList<String>(listOf(*resources.getStringArray(R.array.stat_attack)))
            binding.tvCommandsStat.text = list[0]
            binding.tvTournamentStat.text = list[1]
            binding.tvBlowsStat.text = list[2]
            binding.tvSelectStat.text = list[3]
            binding.tvIntercStat.text = list[4]
            binding.tvFoulsStat.text = list[5]
            binding.tvRatingStat.text = list[6]
            binding.tvOffsidesStat.visibility = View.INVISIBLE
            binding.btnAttack.background = ContextCompat.getDrawable(this@StatisticsActivity, R.drawable.rounded_button)
            binding.btnDefense.background = ContextCompat.getDrawable(this@StatisticsActivity, R.drawable.rounded_button_not_checked)
        }
    }

    private fun changeBackgroundImage(image: Int) {
        when(image) {
            R.drawable.background1 -> binding.constraintSettings.background = ContextCompat.getDrawable(this@StatisticsActivity, R.drawable.background1)
            R.drawable.background2 -> binding.constraintSettings.background = ContextCompat.getDrawable(this@StatisticsActivity, R.drawable.background2)
            R.drawable.background3 -> binding.constraintSettings.background = ContextCompat.getDrawable(this@StatisticsActivity, R.drawable.background3)
        }
    }
}