package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load

class NewsAdapter(private val newsList: MutableList<News>, private val onItemClick: (News) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
    NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.bind(news)
    }

    override fun getItemCount(): Int = newsList.size

    fun updateNews(news: List<News>) {
        newsList.clear()
        newsList.addAll(news)
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivNewsImage: ImageView = itemView.findViewById(R.id.ivNewsImage)
        private val tvNewsTitle: TextView = itemView.findViewById(R.id.tvNewsTitle)
        private val tvNewsSummary: TextView = itemView.findViewById(R.id.tvNewsSummary)

        fun bind(news: News) {
            tvNewsTitle.text = news.title
            tvNewsSummary.text = news.summary
            ivNewsImage.load(news.imageUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_background) // Opcional
            }
            itemView.setOnClickListener { onItemClick(news) }
        }
    }
}
