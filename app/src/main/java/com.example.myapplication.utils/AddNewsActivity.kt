package com.example.myapplication.utils

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.News
import com.example.myapplication.R
import com.google.firebase.firestore.FirebaseFirestore

class AddNewsActivity : AppCompatActivity() {

    private lateinit var etNewsTitle: EditText
    private lateinit var etNewsSummary: EditText
    private lateinit var etNewsContent: EditText
    private lateinit var etNewsAuthor: EditText
    private lateinit var etNewsDate: EditText
    private lateinit var etNewsImageUrl: EditText
    private lateinit var btnSaveNews: Button

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_news)

        etNewsTitle = findViewById(R.id.etNewsTitle)
        etNewsSummary = findViewById(R.id.etNewsSummary)
        etNewsContent = findViewById(R.id.etNewsContent)
        etNewsAuthor = findViewById(R.id.etNewsAuthor)
        etNewsDate = findViewById(R.id.etNewsDate)
        etNewsImageUrl = findViewById(R.id.etNewsImageUrl)
        btnSaveNews = findViewById(R.id.btnSaveNews)

        btnSaveNews.setOnClickListener {
            saveNews()
        }
    }

    private fun saveNews() {
        val title = etNewsTitle.text.toString().trim()
        val summary = etNewsSummary.text.toString().trim()
        val content = etNewsContent.text.toString().trim()
        val author = etNewsAuthor.text.toString().trim()
        val date = etNewsDate.text.toString().trim()
        val imageUrl = etNewsImageUrl.text.toString().trim()

        if (title.isEmpty() || summary.isEmpty() || content.isEmpty() || author.isEmpty() || date.isEmpty() || imageUrl.isEmpty()) {
            showAlert("Error", "Todos los campos son obligatorios")
            return
        }

        val news = News(title, summary, content, author, date, imageUrl)

        db.collection("news")
            .add(news)
            .addOnSuccessListener {
                showAlert("Éxito", "Noticia guardada correctamente") {
                    finish()
                }
            }
            .addOnFailureListener { e ->
                showAlert("Error", "No se pudo guardar la noticia: ${e.message}")
            }
    }

    private fun showAlert(title: String, message: String, onDismiss: (() -> Unit)? = null) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Aceptar") { dialog, _ ->
                dialog.dismiss()
                onDismiss?.invoke()
            }
            .show()
    }
}
