package com.example.myapplication

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding:ActivityMainBinding

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Activar Fragmentos de navbar inferior
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = findViewById(R.id.lateral_menu_drawer)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navigationView = findViewById<NavigationView>(R.id.lateral_nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        val toogle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()
        if (savedInstanceState == null) {
            replaceFragment(HomeConfigurar())
        }



        //Se reecge vista de navbar inferior Para visualizar iconos color original
        val bnv: BottomNavigationView = binding.bottomNavigationView
        bnv.itemIconTintList = null

        //Para colorear titulos
        paintTitle(binding.shopTitle)


        replaceFragment(BuscarFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home_button -> replaceFragment(BuscarFragment())
                R.id.selected_button -> replaceFragment(ComprarFragment())
                R.id.personal_button -> replaceFragment(FavoritosFragment())
                else -> {

                }
            }
            true
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.configurar -> replaceFragment(HomeConfigurar())
            R.id.login -> replaceFragment(LoginFragment())
            R.id.logout -> replaceFragment(LogoutButton())
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    private fun replaceLateralFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.lateral_menu_fragment_container, fragment)
        transaction.commit()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
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
}