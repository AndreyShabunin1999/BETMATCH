package com.googleduck.testapp.view

import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.googleduck.testapp.R
import com.googleduck.testapp.adapter.NewsAdapter
import com.googleduck.testapp.adapter.StatisticsAdapter
import com.googleduck.testapp.api.ApiInterface
import com.googleduck.testapp.api.ApiUtilities
import com.googleduck.testapp.databinding.ActivityNewsBinding
import com.googleduck.testapp.databinding.ActivityStatisticsBinding
import com.googleduck.testapp.model.NewsModel
import com.googleduck.testapp.model.StatisticsAttackModel
import com.googleduck.testapp.repository.NewsRepository
import com.googleduck.testapp.repository.StatisticsDefenseRepository
import com.googleduck.testapp.viewmodel.NewsViewModel
import com.googleduck.testapp.viewmodel.NewsViewModelFactory
import com.googleduck.testapp.viewmodel.StatisticsDefenseViewModel
import com.googleduck.testapp.viewmodel.StatisticsDefenseViewModelFactory

class NewsActivity : AppCompatActivity() {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var binding: ActivityNewsBinding
    private var news = ArrayList<NewsModel>()
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val sharedPref: SharedPreferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE)
        changeBackgroundImage(sharedPref.getInt("IMAGE", R.drawable.background1))

        val apiInterface = ApiUtilities.getInstance().create(ApiInterface::class.java)

        val newsRepository = NewsRepository(apiInterface)

        newsViewModel = ViewModelProvider(this, NewsViewModelFactory(newsRepository)).get(NewsViewModel::class.java)

        binding.rvNews.setHasFixedSize(true)
        binding.rvNews.layoutManager = LinearLayoutManager(this)

        newsViewModel.news.observe(this) {
            for (i in it.indices) {
                news.add(it[i])
            }
            adapter = NewsAdapter(this@NewsActivity, news)
            binding.rvNews.adapter = adapter
        }
    }

    private fun changeBackgroundImage(image: Int) {
        when(image) {
            R.drawable.background1 -> binding.constraintNews.background = ContextCompat.getDrawable(this@NewsActivity, R.drawable.background1)
            R.drawable.background2 -> binding.constraintNews.background = ContextCompat.getDrawable(this@NewsActivity, R.drawable.background2)
            R.drawable.background3 -> binding.constraintNews.background = ContextCompat.getDrawable(this@NewsActivity, R.drawable.background3)
        }
    }
}