package com.example.sqrtdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculateBtn.setOnClickListener {
            if(numberET.text.isNotEmpty())
            {
                var num = (numberET.text.toString()).toDouble()
                if(num < 0)
                {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Negative number")
                    builder.setMessage("The number $num is negative. Want to make it possitive?")
                    builder.setPositiveButton(android.R.string.ok){_, _ ->
                            num *= -1
                            if (num >= 0) {
                                result.text = sqrt(num).toString()
                            }
                    }
                    builder.setNegativeButton(android.R.string.no, null)
                    builder.show()
                }

                if(num >= 0)
                {
                    result.text = sqrt(num).toString()
                }
            }
        }
    }
}
