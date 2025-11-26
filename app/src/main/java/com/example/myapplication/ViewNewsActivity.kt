package com.example.myapplication

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ViewNewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_news)

        val tvNewsTitleDetail: TextView = findViewById(R.id.tvNewsTitleDetail)
        val tvNewsAuthorDetail: TextView = findViewById(R.id.tvNewsAuthorDetail)
        val tvNewsDateDetail: TextView = findViewById(R.id.tvNewsDateDetail)
        val tvNewsSummaryDetail: TextView = findViewById(R.id.tvNewsSummaryDetail)
        val tvNewsContentDetail: TextView = findViewById(R.id.tvNewsContentDetail)

        val news = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("news", News::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<News>("news")
        }

        if (news != null) {
            tvNewsTitleDetail.text = news.title
            tvNewsAuthorDetail.text = news.author
            tvNewsDateDetail.text = news.date
            tvNewsSummaryDetail.text = news.summary
            tvNewsContentDetail.text = news.content
        }
    }
}
