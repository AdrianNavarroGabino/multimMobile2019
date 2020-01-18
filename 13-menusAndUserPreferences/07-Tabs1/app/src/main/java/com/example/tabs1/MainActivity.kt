package com.example.tabs1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Cargamos nuestra toolbar.
        setSupportActionBar(toolbar)
        // Creamos el adapter.
        val adapter = ViewPagerAdapter(supportFragmentManager)
        // Añadimos los fragments y los títulos de pestañas.
        adapter.addFragment(PersonsFragment(), "Personas")
        adapter.addFragment(FruitsFragment(), "Frutas")
        adapter.addFragment(AnimalsFragment(), "Animales")
        // Asociamos el adapter.
        viewPager.adapter = adapter
        // Cargamos las tabs.
        tabs.setupWithViewPager(viewPager)
    }
}
