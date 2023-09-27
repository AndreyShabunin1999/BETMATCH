package com.googleduck.testapp.view

import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.googleduck.testapp.R
import com.googleduck.testapp.adapter.StatisticsAdapter
import com.googleduck.testapp.api.ApiInterface
import com.googleduck.testapp.api.ApiUtilities
import com.googleduck.testapp.databinding.ActivityTablesBinding
import com.googleduck.testapp.model.NewsModel
import com.googleduck.testapp.model.TableModel
import com.googleduck.testapp.repository.StatisticsDefenseRepository
import com.googleduck.testapp.repository.TableRepository
import com.googleduck.testapp.viewmodel.StatisticsDefenseViewModel
import com.googleduck.testapp.viewmodel.StatisticsDefenseViewModelFactory
import com.googleduck.testapp.viewmodel.TableViewModel
import com.googleduck.testapp.viewmodel.TableViewModelFactory

class TablesActivity : AppCompatActivity() {

    private lateinit var tableViewModel: TableViewModel
    var tables = ArrayList<TableModel>()
    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityTablesBinding
    lateinit var sharedPref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTablesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        sharedPref = getSharedPreferences("SHARED_PREF", MODE_PRIVATE)
        changeBackgroundImage(sharedPref.getInt("IMAGE", R.drawable.background1))

        val apiInterface = ApiUtilities.getInstance().create(ApiInterface::class.java)

        val tableRepository = TableRepository(apiInterface)

        tableViewModel = ViewModelProvider(this, TableViewModelFactory(tableRepository)).get(
            TableViewModel::class.java)

        tableViewModel.tables.observe(this) {

            for (i in it.indices) {
                tables.add(it[i])
            }
            goToFragment(ListTableFragment())
        }

        binding.fragmentContainer.setOnClickListener {
            goToFragment(TableFragment())
        }

    }

    fun goToFragment(fragment: Fragment){
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("").commit()
    }

    override fun onBackPressed() {
        val count: Int = supportFragmentManager.backStackEntryCount
        if (count == 1) {
            finish()
            //additional code
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    private fun changeBackgroundImage(image: Int) {
        when(image) {
            R.drawable.background1 -> binding.constraintTableActivity.background = ContextCompat.getDrawable(this@TablesActivity, R.drawable.background1)
            R.drawable.background2 -> binding.constraintTableActivity.background = ContextCompat.getDrawable(this@TablesActivity, R.drawable.background2)
            R.drawable.background3 -> binding.constraintTableActivity.background = ContextCompat.getDrawable(this@TablesActivity, R.drawable.background3)
        }
    }
}