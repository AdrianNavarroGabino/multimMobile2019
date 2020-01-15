package com.example.fragments6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_fragment_descripciones.*

class MainActivity : AppCompatActivity(), ListaFragment.ItemSeleccionable {

    val descripciones = arrayOf("Descripción 1", "Descripción 2", "Descripción 3", "Descripción 4",
        "Descripción 5", "Descripción 6", "Descripción 7", "Descripción 8")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun seHaEscogidoUnItem(i: Int) {
        tvDetalles.text = descripciones[i]
    }
}
