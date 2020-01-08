package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var fragmentNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Mostramos en pantalla el primer Fragment.
        showFragmentOne()

        // Listener del botón.
        btn_change.setOnClickListener {
            if(fragmentNumber == 0)
                showFragmentTwo()
            else if(fragmentNumber == 1)
                showFragmentThree()
            else showFragmentOne()
        }
    }

    private fun showFragmentOne() {
        // Establecemos la transacción de fragments,
        // necesarios para añadir, quitar o reemplazar
        // fragments.
        val transaction = supportFragmentManager.beginTransaction()
        // Instanciamos el fragment a mostrar.
        val fragment = FragmentOne()
        // Indicamos el elemento del layout donde haremos
        // el cambio.
        transaction.replace(R.id.fragment_holder, fragment)
        // Ponemos valor null para indicar que no estamos interesados
        // en volver a ese fragment más tarde, en caso contrario,
        // pondríamos el nombre del fragment, por ejemplo fragment.TAG,
        // aprovechando la variable creada en la clase.
        transaction.addToBackStack(null)
        // Mostramos el fragment
        transaction.commit()
        fragmentNumber = 0
    }

    // Igual que showFragmentOne() pero para el segundo.
    private fun showFragmentTwo() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = FragmentTwo()
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        fragmentNumber = 1
    }

    private fun showFragmentThree() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = FragmentThree()
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        fragmentNumber = 2
    }
}
