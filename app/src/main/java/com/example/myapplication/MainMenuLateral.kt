package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainMenuLateral:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.main_menu_lateral)
    }
    fun toShop(view: View) {
        Toast.makeText(this, "abriendo shop", Toast.LENGTH_LONG).show()
        startActivity(Intent(this, Main::class.java))
    }
    fun toChat(view: View) {
        Toast.makeText(this, "abriendo chat", Toast.LENGTH_LONG).show()
    }
    fun toWiki(view: View) {
        Toast.makeText(this, "abriendo wiki", Toast.LENGTH_LONG).show()
    }


}
