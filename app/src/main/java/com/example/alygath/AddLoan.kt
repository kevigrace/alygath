package com.example.alygath

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.alygath.database.DbHelper
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class AddLoan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_loan)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn = findViewById<MaterialButton>(R.id.submit)
        val debtor = findViewById<TextInputLayout>(R.id.debtor)
        val date = findViewById<TextInputLayout>(R.id.date)
        val due_date = findViewById<TextInputLayout>(R.id.due_date)
        val amount = findViewById<TextInputLayout>(R.id.amount)

        btn.setOnClickListener {
            val db = DbHelper(this@AddLoan)

            val debtorVal = debtor.editText?.text.toString()
            val dateVal = date.editText?.text.toString()
            val dueDate = due_date.editText?.text.toString()
            val amountVal = amount.editText?.text.toString()

            db.createLoan(debtorVal,dateVal,dueDate, amountVal.toInt(),"Pending")
        }
    }
}