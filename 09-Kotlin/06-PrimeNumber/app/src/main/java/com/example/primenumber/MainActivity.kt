package com.example.primenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkBtn.setOnClickListener {
            if (number.text.toString() == "") {
                Snackbar.make(
                    root_layout,
                    "Enter a number",
                    Snackbar.LENGTH_LONG
                ).setAction(
                    "Dismiss"
                ) {
                }.show()
            } else {
                result.text =
                    if (isPrime(Integer.parseInt(number.text.toString()))) "It's prime" else "It's not prime"
            }
        }
    }

    fun isPrime(n: Int): Boolean {
        if(n == 1)
        {
            return false
        }

        for(i in 2..n/2)
        {
            if(n % i == 0)
            {
                return false
            }
        }

        return true
    }
}
