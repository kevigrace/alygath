package com.example.alygath

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.alygath.fragments.DebtFragment
import com.example.alygath.fragments.LoanFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Homepage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_homepage)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val frame = findViewById<FrameLayout>(R.id.fragHolder)
        val bottomBar = findViewById<BottomNavigationView>(R.id.bottomnav)
        supportFragmentManager.beginTransaction().replace(R.id.fragHolder, LoanFragment()).commitNow()


        bottomBar.setOnItemSelectedListener {
            if (it.itemId == R.id.loans){
                supportFragmentManager.beginTransaction().replace(R.id.fragHolder, LoanFragment()).commitNow()
            }else if (it.itemId == R.id.debts){
                supportFragmentManager.beginTransaction().replace(R.id.fragHolder, DebtFragment()).commitNow()
            }

            true
        }
    }
}