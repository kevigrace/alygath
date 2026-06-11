package com.example.alygath.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){

    companion object{
        private const val DATABASE_NAME = "sample.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_USERS = "users"

        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_EMAIL = "email"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_USERS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT NOT NULL,
                $COLUMN_EMAIL TEXT NOT NULL
            )
        """.trimIndent()

        val createLoanstable = "CREATE TABLE \"loans\" (\n" +
                "\t\"id\"\tINTEGER,\n" +
                "\t\"debtor\"\tTEXT,\n" +
                "\t\"date\"\tTEXT,\n" +
                "\t\"amount\"\tINTEGER,\n" +
                "\t\"status\"\tTEXT,\n" +
                "\t\"due_date\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
                ");".trimIndent()

        val createDebtsTable = "CREATE TABLE \"debts\" (\n" +
                "\t\"id\"\tINTEGER,\n" +
                "\t\"creditor\"\tTEXT,\n" +
                "\t\"date\"\tTEXT,\n" +
                "\t\"amount\"\tINTEGER,\n" +
                "\t\"status\"\tTEXT,\n" +
                "\t\"due_date\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
                ");".trimIndent()

        db.execSQL(createTable)
        db.execSQL(createLoanstable)
        db.execSQL(createDebtsTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun createLoan(debtor: String, date: String, due_date: String, amount: Int, status: String): Long{
        val db = writableDatabase
        val values = ContentValues().apply {
            put("amount", amount)
            put("status", status)
            put("due_date", due_date)
            put("date", date)
            put("debtor", debtor)
        }

        return db.insert("loans",null,values)
    }

    fun insertUser(name: String, email: String): Long {
        val db = writableDatabase

        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_EMAIL, email)
        }

        return db.insert(TABLE_USERS, null, values)
    }

    fun getAllUsers(): Cursor {
        val db = readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_USERS", null)
    }

    fun updateUser(id: Int, name: String, email: String): Int {
        val db = writableDatabase

        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_EMAIL, email)
        }

        return db.update(
            TABLE_USERS,
            values,
            "$COLUMN_ID = ?",
            arrayOf(id.toString())
        )
    }

    fun deleteUser(id: Int): Int {
        val db = writableDatabase

        return db.delete(
            TABLE_USERS,
            "$COLUMN_ID = ?",
            arrayOf(id.toString())
        )
    }
}