package com.example.alygath

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val forgot = findViewById<MaterialTextView>(R.id.forgot)
        val register = findViewById<MaterialTextView>(R.id.register)
        val login = findViewById<MaterialButton>(R.id.login)

        forgot.setOnClickListener {
            val intent = Intent(this@MainActivity, Forgotpassword::class.java)
            startActivity(intent)
        }

        register.setOnClickListener {
            val intent = Intent(this@MainActivity, Register::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {
            val intent = Intent(this@MainActivity, Homepage::class.java)
            startActivity(intent)
            finish()
        }
    }
}