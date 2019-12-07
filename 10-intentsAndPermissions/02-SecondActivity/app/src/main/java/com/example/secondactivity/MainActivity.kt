package com.example.secondactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        conduccion.setOnClickListener {
            val myIntent = Intent(this, Conduccion::class.java)
            startActivity(myIntent)
        }

        conveccion.setOnClickListener {
            val myIntent = Intent(this, Conveccion::class.java)
            startActivity(myIntent)
        }

        radiacion.setOnClickListener {
            val myIntent = Intent(this, Radiacion::class.java)
            startActivity(myIntent)
        }

        toFahr.setOnClickListener {
            if(!celsius.text.isEmpty())
            {
                val myIntent = Intent(this, Fahrenheit::class.java).apply {
                    putExtra("cels", celsius.text.toString())
                }

                startActivity(myIntent)
            }
        }
    }
}
