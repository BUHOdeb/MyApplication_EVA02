package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val btnRecover = findViewById<Button>(R.id.btnRecover)

        val prefs: SharedPreferences = getSharedPreferences("datosUsuario", MODE_PRIVATE)

        // Botón de login
        btnLogin.setOnClickListener {
            val emailIngresado = etEmail.text.toString()
            val passwordIngresada = etPassword.text.toString()

            val emailGuardado = prefs.getString("email", "")
            val passwordGuardada = prefs.getString("password", "")

            if (emailIngresado == emailGuardado && passwordIngresada == passwordGuardada && emailIngresado.isNotEmpty()) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, DashboardActivity::class.java))
            } else {
                Toast.makeText(this, "Datos incorrectos o usuario no registrado", Toast.LENGTH_SHORT).show()
            }
        }

        btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnRecover.setOnClickListener {
            startActivity(Intent(this, RecoverActivity::class.java))
        }
    }
}
