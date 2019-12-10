package com.example.openwebpage

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openBtn.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://www.iessanvicente.com")
            )
            startActivity(intent)
        }
    }
}
