package com.example.fragments3

import android.graphics.Color.GREEN
import android.graphics.Color.RED
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val NUM_FRAGMENT = "fragment"
        const val COLOR_BACK = "color_frag"
    }
    private var numfrag = 0
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
        bundle.putInt(COLOR_BACK, (if ((numfrag % 2) == 0) RED else GREEN))
        fragment.arguments = bundle
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
