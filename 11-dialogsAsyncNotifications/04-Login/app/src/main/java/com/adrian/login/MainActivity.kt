package com.adrian.login

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginBtn.setOnClickListener{
            myCustomAlertDialog()
        }
    }

    private fun myCustomAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            val inflater = layoutInflater
            setView(inflater.inflate(R.layout.login, null))
            setPositiveButton(android.R.string.ok) { dialog, _ ->
                val name = (dialog as AlertDialog).username.text
                val pass = dialog.password.text
                Toast.makeText(
                    context,
                    "User: $name\nPass: $pass",
                    Toast.LENGTH_SHORT
                ).show()
            }
            setNegativeButton(android.R.string.no) { dialog, _ ->
                Toast.makeText(
                    context,
                    android.R.string.no,
                    Toast.LENGTH_SHORT
                ).show()
                dialog.dismiss()
            }
        }
        builder.show()
    }
}
