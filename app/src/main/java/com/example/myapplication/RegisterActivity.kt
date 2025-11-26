package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var etNewEmail: EditText
    private lateinit var etNewPassword: EditText
    private lateinit var btnSaveAccount: Button
    private lateinit var btnBackLogin: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etNewEmail = findViewById(R.id.etNewEmail)
        etNewPassword = findViewById(R.id.etNewPassword)
        btnSaveAccount = findViewById(R.id.btnSaveAccount)
        btnBackLogin = findViewById(R.id.btnBackLogin)

        auth = FirebaseAuth.getInstance()

        btnSaveAccount.setOnClickListener {
            registerUser()
        }

        btnBackLogin.setOnClickListener {
            finish()
        }
    }

    private fun registerUser() {
        val email = etNewEmail.text.toString().trim()
        val password = etNewPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Correo y contraseña son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Fallo en el registro: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
