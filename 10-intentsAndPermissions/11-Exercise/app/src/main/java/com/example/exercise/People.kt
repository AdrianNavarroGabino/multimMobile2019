package com.example.exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_people.*
import java.io.BufferedReader
import java.io.InputStreamReader

class People : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people)

        val file = BufferedReader(
            InputStreamReader(openFileInput("data.txt"))
        )

        val text = file.use(BufferedReader::readText)

        resultTV.text = text

        backBtn.setOnClickListener {
            Intent().apply {}

            finish()
        }
    }
}
