package com.example.mailvalidating

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        validateBtn.setOnClickListener {
            if(mailET.text.toString() != mailET2.text.toString())
            {
                Toast.makeText(applicationContext, "The mails are not equals", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(applicationContext, "Perfect", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
