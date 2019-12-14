package com.example.exercise

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_people.*
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

class People : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people)

        try
        {
            readFile()
        }
        catch(e: Exception)
        {

        }

        backBtn.setOnClickListener {
            finish()
        }
    }

    fun readFile() {
        val sdRoute = baseContext.getExternalFilesDir(null)?.absolutePath
        val myDir = File(sdRoute, "MyData")
        val physicalFile = File(myDir, "data.txt")
        val file = BufferedReader(InputStreamReader(FileInputStream(physicalFile)))
        val text = file.use(BufferedReader::readText)
        resultTV.text = text
    }
}
