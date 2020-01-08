package com.example.fragments4

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NewFragment.DatoDevuelto  {

    override fun datoActualizado(dato: String) {
        editResult.text = dato
    }

    companion object {
        const val NUM_FRAGMENT = "fragment"
        const val COLOR_BACK = "color_frag"
        const val TEXT = "input_text"
    }
    private var numfrag = 0
    private var text = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showFragment()

        btn_change.setOnClickListener {
            showFragment()
        }
    }

    private fun showFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = NewFragment()
        // Creamos una variable de tipo Bundle()
        // para "empaquetar" los datos a pasar.
        val bundle = Bundle()
        bundle.putInt(NUM_FRAGMENT, ++numfrag)
        bundle.putInt(COLOR_BACK, (if ((numfrag % 2) == 0) Color.RED else Color.GREEN))
        bundle.putString(TEXT, text)
        fragment.arguments = bundle
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}

