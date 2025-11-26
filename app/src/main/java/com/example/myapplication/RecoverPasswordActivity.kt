package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import java.util.UUID

class RecoverPasswordActivity : AppCompatActivity() {

    private lateinit var etEmailRecover: EditText
    private lateinit var btnRecoverPassword: Button
    private lateinit var tvNewPassword: TextView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover_password)

        etEmailRecover = findViewById(R.id.etEmailRecover)
        btnRecoverPassword = findViewById(R.id.btnRecoverPassword)
        tvNewPassword = findViewById(R.id.tvNewPassword)

        auth = FirebaseAuth.getInstance()

        btnRecoverPassword.setOnClickListener {
            recoverPassword()
        }
    }

    private fun recoverPassword() {
        val email = etEmailRecover.text.toString().trim()

        if (email.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese su correo electrónico", Toast.LENGTH_SHORT).show()
            return
        }

        auth.fetchSignInMethodsForEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val signInMethods = task.result?.signInMethods
                    if (signInMethods.isNullOrEmpty()) {
                        Toast.makeText(this, "No existe ningún usuario con este correo electrónico", Toast.LENGTH_SHORT).show()
                    } else {
                        val newPassword = UUID.randomUUID().toString().substring(0, 8)
                        tvNewPassword.text = "Nueva contraseña: $newPassword (Simulación)"
                        Toast.makeText(this, "Esta es una simulación. La contraseña no se ha actualizado en Firebase.", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this, "Error al verificar el correo electrónico", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
