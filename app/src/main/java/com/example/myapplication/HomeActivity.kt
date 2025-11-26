package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.utils.AddNewsActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity() {

    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var fabAddNews: FloatingActionButton
    private lateinit var btnLogout: Button
    private lateinit var newsAdapter: NewsAdapter
    private val newsList = mutableListOf<News>()

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        newsRecyclerView = findViewById(R.id.newsRecyclerView)
        fabAddNews = findViewById(R.id.fabAddNews)
        btnLogout = findViewById(R.id.btnLogout)

        setupRecyclerView()
        loadNews()

        fabAddNews.setOnClickListener {
            startActivity(Intent(this, AddNewsActivity::class.java))
        }

        btnLogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter(newsList) { news ->
            val intent = Intent(this, ViewNewsActivity::class.java)
            intent.putExtra("news", news)
            startActivity(intent)
        }
        newsRecyclerView.adapter = newsAdapter
        newsRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun loadNews() {
        db.collection("news").get()
            .addOnSuccessListener { result ->
                val news = result.toObjects(News::class.java)
                newsAdapter.updateNews(news)
            }
            .addOnFailureListener { exception ->
                // Handle error
            }
    }
    
    override fun onResume() {
        super.onResume()
        loadNews()
    }
}
