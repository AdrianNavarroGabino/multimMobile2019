package com.example.secondactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fahrenheit.*

class Fahrenheit : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fahrenheit)

        var celsius = intent.getStringExtra("cels")

        var fahrenheit = celsius.toDouble() * 9 / 5 + 32

        fahr.text = fahrenheit.toInt().toString()

        backBtn.setOnClickListener {
            val intentResult : Intent = Intent().apply {
                putExtra("fahrBack", fahrenheit.toInt())
            }

            setResult(Activity.RESULT_OK, intentResult)
            finish()
        }
    }
}