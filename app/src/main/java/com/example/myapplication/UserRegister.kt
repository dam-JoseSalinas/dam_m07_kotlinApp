package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class UserRegister : AppCompatActivity() {
    lateinit var fullname: EditText;
    lateinit var username: EditText
    lateinit var email: EditText
    lateinit var password: EditText
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_register)
        fullname = findViewById<EditText>(R.id.register_fullname)
        username = findViewById<EditText>(R.id.register_username)
        email = findViewById<EditText>(R.id.register_email)
        password = findViewById<EditText>(R.id.register_password)

        val register_btn: Button = findViewById<Button>(R.id.create_user_btn)

        register_btn.setOnClickListener {
            if (validacion1() && validacion2()) {
                //register
                val db = Database(this, null)
                db.addUser(fullname.text.toString(), username.text.toString(), email.text.toString(), password.text.toString())
                Toast.makeText(this, "Usuario creado correctamente", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, UserLogin::class.java))
            }
        }
    }

    private fun validacion2(): Boolean {
        val db = Database(this, null)
        val result = db.getUserByUsername(username.text.toString())
        if (result!!.moveToFirst()) {
            Toast.makeText(this, "Nombre de usuario existente", Toast.LENGTH_LONG).show()
            username?.setError("change")
            return false
        } else {
            return true
        }
    }

    private fun validacion1(): Boolean {
        if (fullname.text.toString().equals("") || username.text.toString().equals("") || email.text.toString().equals("") || password.text.toString().equals("")) {
            if (fullname.text.toString().equals("")) {
                fullname?.setError("required")
            }
            if (username.text.toString().equals("")){
                username?.setError("required")
            }
            if (email.text.toString().equals("")) {
                email?.setError("required")
            }
            if (password.text.toString().equals("")) {
                password?.setError("required")
            }
            return false
        }
        return true
    }
}