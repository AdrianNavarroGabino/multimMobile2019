package com.example.secondactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

                startActivityForResult(myIntent, 5435)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data:
    Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 5435) {
            if (resultCode == Activity.RESULT_OK) {
                val result = data?.getIntExtra(
                    "fahrBack", 0).toString()
                resultBack.text = result
                resultBack.visibility = View.VISIBLE
            }
        }
    }
}
