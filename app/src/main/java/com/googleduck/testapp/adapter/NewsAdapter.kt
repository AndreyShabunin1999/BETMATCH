package com.googleduck.testapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.googleduck.testapp.R
import com.googleduck.testapp.model.NewsModel

class NewsAdapter(private var context: Context, private var news: ArrayList<NewsModel>): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title_news)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_descriptions_news)
        val tvDate: TextView = itemView.findViewById(R.id.tv_date_news)
        val imgNews: ImageView = itemView.findViewById(R.id.img_news)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news , parent , false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.tvTitle.text = news[position].tittle
        holder.tvDescription.text = news[position].text
        holder.tvDate.text = news[position].date

        Glide
            .with(context)
            .load(news[position].img)
            .into(holder.imgNews)
    }
}