package com.example.i_fit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)  // Define o layout

        // Inicializa as coisas do xml do layout
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val signupButton = findViewById<Button>(R.id.signUpButton)

        // Pega click do botão de cadastrar
        signupButton.setOnClickListener {
            // Obtém o texto dos campos, removendo espaços em branco desnecessários com o trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Verifica se os campos de email e senha não estão vazios
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Realiza a tentativa de cadastro usando Firebase Authentication
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Se o cadastro for bem-sucedido, mostra uma mensagem e navega para a MainActivity e usa o finish() para matar a tela da ram, assim nao da para voltar pra ela apertando o botao voltar
                            Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        } else {
                            // Se o cadastro falhar, mostra uma mensagem de erro
                            Toast.makeText(this, "Erro ao cadastrar: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                // Mostra uma mensagem se os campos de email ou senha estiverem vazios
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}