package com.example.sqlitebasicactivity

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqlitebasicactivity.MainActivity.Companion.peopleDBHelper
import kotlinx.android.synthetic.main.activity_create.*
import kotlinx.android.synthetic.main.content_main.*

class CreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        insertarBtn.setOnClickListener {
            if (nombreET.text.isNotBlank() && apellidoET.text.isNotBlank()) {
                // Insertamos en la tabla.
                peopleDBHelper.addPerson(
                    nombreET.text.toString(),
                    apellidoET.text.toString()
                )
                // Limpiamos los EditText después de la inserción.
                nombreET.text.clear()
                apellidoET.text.clear()

                finish()
            } else {
                myToast("No pueden estar los campos vacíos.")
            }
        }
    }

    private fun myToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
