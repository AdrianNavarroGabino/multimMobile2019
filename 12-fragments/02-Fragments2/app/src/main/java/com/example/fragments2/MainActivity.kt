package com.example.fragments2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        show()

        firstBtn.setOnClickListener {
            show()
        }

        secondBtn.setOnClickListener {
            change()
        }
    }

    private fun show() {
        // Establecemos la transacción de fragments,
        // necesarios para añadir, quitar o reemplazar
        // fragments.
        val transaction = supportFragmentManager.beginTransaction()
        // Instanciamos el fragment a mostrar.
        val fragment = Fragment1()
        val fragment2 = Fragment2()
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.replace(R.id.fragment_holder2, fragment2)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun change() {
        // Establecemos la transacción de fragments,
        // necesarios para añadir, quitar o reemplazar
        // fragments.
        val transaction = supportFragmentManager.beginTransaction()
        // Instanciamos el fragment a mostrar.
        val fragment = Fragment1()
        val fragment2 = Fragment2()
        transaction.replace(R.id.fragment_holder2, fragment)
        transaction.replace(R.id.fragment_holder, fragment2)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
