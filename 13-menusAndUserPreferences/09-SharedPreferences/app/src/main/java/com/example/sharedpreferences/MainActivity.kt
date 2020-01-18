package com.example.sharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pref = applicationContext.getSharedPreferences("file", 0)
        val myData = pref.getString("name", "Default name")
        resultTV.text = myData.toString()

        saveBtn.setOnClickListener {
            val editor = pref.edit()
            editor.putString("name", inputTV.text.toString())
            editor.apply()

            resultTV.text = inputTV.text
            inputTV.editableText.clear()
        }
    }
}
