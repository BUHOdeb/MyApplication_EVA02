package com.example.myapplication

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecoverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover)

        val prefs: SharedPreferences = getSharedPreferences("datosUsuario", MODE_PRIVATE)
        val email = prefs.getString("email", "No registrado")
        val password = prefs.getString("password", "No registrada")

        val info = findViewById<TextView>(R.id.tvInfo)
        val btnBack = findViewById<Button>(R.id.btnBackLogin)

        info.text = "Correo: $email\nContraseña: $password"

        btnBack.setOnClickListener {
            finish()
        }
    }
}
