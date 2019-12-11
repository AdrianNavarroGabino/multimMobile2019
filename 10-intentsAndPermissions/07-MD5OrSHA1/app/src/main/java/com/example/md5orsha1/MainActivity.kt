package com.example.md5orsha1

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

        encodeBtn.setOnClickListener {
            if(!inputEt.text.isEmpty())
            {
                val myIntent = Intent(this, Encrypt::class.java).apply {
                    putExtra("inputText", inputEt.text.toString())
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
                val result = data?.getStringExtra(
                    "encoded").toString()
                resultTv.text = result
                resultTv.visibility = View.VISIBLE
            }
        }
    }
}
