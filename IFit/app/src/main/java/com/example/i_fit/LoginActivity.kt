package com.example.i_fit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)  // Define o layout

        // Inicializa as coisas do xml do layout
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val signupButton = findViewById<Button>(R.id.signupButton)

        // pega o click do botão de login
        loginButton.setOnClickListener {
            // Obtém o texto dos EditTexts, removendo espaços em branco desnecessários no.trim
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Verifica se os campos de email e senha não estão vazios
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Realiza a tentativa de login usando Firebase Authentication
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Se o login for bem-sucedido, navega para a MainActivity
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        } else {

                            // Aqui personalizamos a mensagem de erro
                            val errorMessage = when (task.exception) {
                                is FirebaseAuthInvalidCredentialsException -> "O email ou senha está incorreto."
                                else -> task.exception?.message ?: "Erro desconhecido no login."
                            }
                            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                // Mostra uma mensagem se os campos de email ou senha estiverem vazios
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Configura o listener do botão de cadastro
        signupButton.setOnClickListener {
            // Inicia a atividade de cadastro quando o botão é pressionado
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}