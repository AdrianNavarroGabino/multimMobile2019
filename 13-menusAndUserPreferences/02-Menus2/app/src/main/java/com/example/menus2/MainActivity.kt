package com.example.menus2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu1, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId)
        {
            R.id.menu_hola -> {
                textview.text = "Hola"
                true
            }
            R.id.menu_saludar -> {
                textview.text = "Hola"
                true
            }
            R.id.menu_toast -> {
                Toast.makeText(this, "Hola", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_snackbar -> {
                Snackbar.make(
                    root_layout,
                    "Hola",
                    Snackbar.LENGTH_LONG
                ).setAction(
                    "Dismiss"
                ) {
                }.show()
                true
            }
            R.id.menu_dialog -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Saludo")
                builder.setMessage("Hola")
                builder.setPositiveButton(android.R.string.yes, null)
                builder.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
