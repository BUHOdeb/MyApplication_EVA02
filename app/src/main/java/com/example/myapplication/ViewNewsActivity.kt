package com.example.myapplication

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.load

class ViewNewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_news)

        val tvNewsTitleDetail: TextView = findViewById(R.id.tvNewsTitleDetail)
        val tvNewsAuthorDetail: TextView = findViewById(R.id.tvNewsAuthorDetail)
        val tvNewsDateDetail: TextView = findViewById(R.id.tvNewsDateDetail)
        val ivNewsImageDetail: ImageView = findViewById(R.id.ivNewsImageDetail)
        val tvNewsBodyDetail: TextView = findViewById(R.id.tvNewsBodyDetail)

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
            tvNewsBodyDetail.text = news.content // 'content' tiene el cuerpo completo
            ivNewsImageDetail.load(news.imageUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_background)
            }
        }
    }
}
