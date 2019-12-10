package com.example.ratingbar

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_rating.*

class Rating : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        okBtn.setOnClickListener {
            val intentResult : Intent = Intent().apply {
                putExtra("ratingValue", ratingBar.rating)
            }

            setResult(Activity.RESULT_OK, intentResult)
            finish()
        }
    }
}
