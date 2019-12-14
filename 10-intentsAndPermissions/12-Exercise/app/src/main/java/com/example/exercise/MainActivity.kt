package com.example.exercise

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveBtn.setOnClickListener {

            if(nameET.text.isNotEmpty() && mailET.text.isNotEmpty()) {
                try
                {
                    addContact()
                }
                catch(e: Exception)
                {

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

    fun addContact() {
        val sdRoute = baseContext.getExternalFilesDir(null)?.absolutePath
        val myDir = File(sdRoute, "MyData")
        if(!myDir.exists()) { myDir.mkdir() }
        val physicalFile = File(myDir, "data.txt")
        val file = PrintWriter(FileOutputStream(
            physicalFile,
            true))
        file.appendln("${nameET.text} - ${mailET.text}")
        file.close()
        nameET.text.clear()
        mailET.text.clear()
    }
}
