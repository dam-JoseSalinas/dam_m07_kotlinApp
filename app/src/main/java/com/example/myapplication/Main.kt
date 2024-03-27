package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.MainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class Main : AppCompatActivity() {
    private lateinit var binding:MainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //COLORES DE ICONOS
            //Activar binding
            binding = MainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            //Para visualizar iconos color original
            val bnv: BottomNavigationView = binding.bottomNavigationView
            bnv.itemIconTintList = null
            //Para colorear titulo
            paintTitle(binding.titleShop)

        replaceFragment(BuscarFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.buscar_btn -> replaceFragment(BuscarFragment())
                R.id.compras_btn -> replaceFragment(ComprarFragment())
                R.id.analizar_btn -> replaceFragment(AnalizarFragment())
                else -> {

                }
            }
            true
        }

    }


    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_container, fragment)
        fragmentTransaction.commit()
    }

    fun paintTitle(textview: TextView) {
        val myShader: Shader = LinearGradient(
            0f, 0f, 0f, 100f,
            Color.rgb(255, 250, 160), Color.rgb(10, 0, 46),
            Shader.TileMode.CLAMP
        )
        textview.paint.setShader(myShader)
    }

    fun openMenu(view: View) {
        startActivity(Intent(this, MainMenuLateral::class.java))
    }
}