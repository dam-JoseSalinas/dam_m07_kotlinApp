package com.example.myapplication

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class UserLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_login)

        val register_btn: Button = findViewById<Button>(R.id.register_btn)
        register_btn.setOnClickListener {
            startActivity(Intent(this, UserRegister::class.java))
        }

        val login_btn: Button = findViewById<Button>(R.id.login_btn)
        login_btn.setOnClickListener {
            val db = Database(this, null)
            val username = findViewById<EditText>(R.id.username).text.toString()
            val password = findViewById<EditText>(R.id.password).text.toString()
            val result = db.getUser(username, password)
            if (result!!.moveToFirst()) {
                startActivity(Intent(this, Main::class.java))
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_LONG).show()
            }
        }



    }
}