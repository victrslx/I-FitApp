package com.example.i_fit

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

    }
    // Pega click de um text view, infelizmente tem que fazer essa func
    fun onClick(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}