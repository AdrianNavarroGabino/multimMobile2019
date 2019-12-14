package com.example.exercise

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.io.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveBtn.setOnClickListener {

            if(nameET.text.isNotEmpty() && mailET.text.isNotEmpty())
            {
                try {
                    val stream: FileOutputStream = openFileOutput(
                        "data.txt", Context.MODE_APPEND
                    )

                    val file = PrintWriter(stream)
                    file.appendln("${nameET.text} - ${mailET.text}")
                    file.close()
                    nameET.text.clear()
                    mailET.text.clear()
                } catch (e: Exception) {
                    Toast.makeText(this, "ERROR: " + e.stackTrace, Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(this, "Enter name and mail", Toast.LENGTH_SHORT).show()
            }
        }

        showBtn.setOnClickListener {
            val myIntent = Intent(this, People::class.java)
            startActivity(myIntent)
        }
    }
}
