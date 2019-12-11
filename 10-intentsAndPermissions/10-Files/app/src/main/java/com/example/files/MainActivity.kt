package com.example.files

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.PrintWriter
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveBtn.setOnClickListener {
            try {
                val stream: FileOutputStream = openFileOutput(
                    "data.txt", Context.MODE_PRIVATE
                )

                val file = PrintWriter(stream)
                file.println(input.text)
                file.close()
                input.text.clear()
                loadedText.text = ""

            } catch (e: Exception) {
                Toast.makeText(this, "ERROR: " + e.stackTrace, Toast.LENGTH_SHORT).show()
            }
        }

        loadBtn.setOnClickListener {
            val file = BufferedReader(
                InputStreamReader(openFileInput("data.txt"))
            )

            val text = file.use(BufferedReader::readText)

            loadedText.text = text
        }
    }
}
