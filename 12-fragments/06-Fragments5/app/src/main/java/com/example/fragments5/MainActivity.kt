package com.example.fragments5

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_new.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val NUM_FRAGMENT = "fragment"
        const val COLOR_BACK = "color_frag"
        const val TEXT = "input_text"
    }

    private var numfrag = 0
    private var text = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        val newFragment = New()

        transaction.replace(R.id.fragment_holder, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()

        toFragmentBtn.setOnClickListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.fragment_holder) as New

            fragment.resultFragment.text = inputTextActivity.text.toString()
        }
    }

}