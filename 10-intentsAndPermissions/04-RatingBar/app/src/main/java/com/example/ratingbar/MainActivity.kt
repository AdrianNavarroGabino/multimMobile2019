package com.example.ratingbar

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

        rateBtn.setOnClickListener {
            val myIntent = Intent(this, Rating::class.java).apply {
            }

            startActivityForResult(myIntent, 5435)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data:
    Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 5435) {
            if (resultCode == Activity.RESULT_OK) {
                val result = data?.getFloatExtra(
                    "ratingValue", 0.0F).toString()
                ratingTv.text = result
                ratingTv.visibility = View.VISIBLE
            }
        }
    }
}
