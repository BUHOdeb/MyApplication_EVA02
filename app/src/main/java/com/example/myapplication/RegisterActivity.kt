package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etEmail = findViewById<EditText>(R.id.etNewEmail)
        val etPassword = findViewById<EditText>(R.id.etNewPassword)
        val btnSave = findViewById<Button>(R.id.btnSaveAccount)
        val btnBack = findViewById<Button>(R.id.btnBackLogin)

        // Guardar datos
        btnSave.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val prefs: SharedPreferences = getSharedPreferences("datosUsuario", MODE_PRIVATE)
                val editor = prefs.edit()
                editor.putString("email", email)
                editor.putString("password", password)
                editor.apply()

                Toast.makeText(this, "Cuenta registrada correctamente", Toast.LENGTH_SHORT).show()
            }
        }

        // Volver al Login
        btnBack.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
