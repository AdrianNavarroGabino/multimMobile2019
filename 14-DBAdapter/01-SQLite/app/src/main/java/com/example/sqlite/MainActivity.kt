package com.example.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var peopleDBHelper: MyDBOpenHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Instanciamos el objeto MyDBOpenHelper.
        peopleDBHelper = MyDBOpenHelper(this, null)

        // Botón insertar.
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
            } else {
                myToast("No pueden estar los campos vacíos.")
            }
        }

        // Botón actualizar.
        var oldName: String? = null
        actualizarBtn.setOnClickListener {
            if (nombreET.text.isNotBlank() && oldName.isNullOrEmpty()) {
                oldName = nombreET.text.toString()
                myToast("Indica ahora el nuevo nombre y "
                        + "pulsa el botón actualizar.")
                nombreET.text.clear()
            } else if (nombreET.text.isNotBlank() && oldName!!.isNotBlank()) {
                // Actualizamos en la tabla.
                peopleDBHelper.updatePerson(
                    oldName!!,
                    nombreET.text.toString()
                )
                // Limpiamos los EditText después de la actualización.
                nombreET.text.clear()
                apellidoET.text.clear()
                oldName = null
            } else {
                myToast("El nombre no puede estar vacío para actualizar.")
            }
        }

        // Botón eliminar.
        eliminarBtn.setOnClickListener {
            if (nombreET.text.isNotBlank()) {
                // Eliminamos en la tabla el nombre.
                peopleDBHelper.delPerson(nombreET.text.toString())
                // Limpiamos los EditText después de la eliminación.
                nombreET.text.clear()
                apellidoET.text.clear()
            } else {
                myToast("El nombre no puede estar vacío para eliminar.")
            }
        }
    }
    private fun myToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
