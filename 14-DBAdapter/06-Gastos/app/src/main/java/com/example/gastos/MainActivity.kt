package com.example.gastos

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var gastosDBHelper: MyDBOpenHelper
        val itemsGastos = mutableListOf<String>()
        val itemsCategorias = mutableListOf<String>()
        val itemsCuentas = mutableListOf<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH] + 1
        val day = calendar[Calendar.DAY_OF_MONTH]

        fechaET.setText(year.toString() + "-" + month.toString() + "-" + day.toString())

        gastosDBHelper = MyDBOpenHelper(this, null)

        val db: SQLiteDatabase =
            MainActivity.gastosDBHelper.readableDatabase

        val cursor: Cursor = db.rawQuery(
            " SELECT * FROM ${MyDBOpenHelper.TABLE_GASTOS}",
            null)


        if (cursor.moveToFirst()) {
            do {
                itemsGastos.add(cursor.getInt(0).toString() + " - " +
                    cursor.getString(1).toString() + " " +
                    cursor.getString(2).toString() + " " +
                    cursor.getDouble(3).toString() + "\n" +
                    cursor.getString(4).toString() +
                    cursor.getString(5).toString())
            } while (cursor.moveToNext())
        }

        val cursor2: Cursor = db.rawQuery(
            " SELECT * FROM ${MyDBOpenHelper.TABLE_CATEGORIAS}",
            null)


        if (cursor2.moveToFirst()) {
            do {
                itemsCategorias.add(cursor2.getString(1).toString())
            } while (cursor2.moveToNext())
        }

        val cursor3: Cursor = db.rawQuery(
            " SELECT * FROM ${MyDBOpenHelper.TABLE_CUENTAS}",
            null)


        if (cursor3.moveToFirst()) {
            do {
                itemsCuentas.add(cursor3.getString(1).toString())
            } while (cursor3.moveToNext())
        }

        elegirFechaBtn.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this@MainActivity,
                OnDateSetListener { datePicker, yearC, monthC, dayC ->
                    fechaET.setText(yearC.toString() + "-" + (monthC + 1).toString() + "-" + dayC.toString())
                }, year, month, day
            )

            datePickerDialog.show()
        }

        guardarBtn.setOnClickListener {
            if (fechaET.text.isNotBlank() && detallesET.text.isNotBlank() && importeET.text.isNotBlank()) {
                // Insertamos en la tabla.
                gastosDBHelper.addGasto(
                    fechaET.text.toString(), detallesET.text.toString(),
                    (importeET.text.toString()).toDouble(), categoriaSpinner.selectedItemPosition + 1, cuentaSpinner.selectedItemPosition + 1
                )
                // Limpiamos los EditText después de la inserción.
                fechaET.setText(year.toString() + "-" + month.toString() + "-" + day.toString())
                importeET.text.clear()
                detallesET.text.clear()
                categoriaSpinner.setSelection(0)
                cuentaSpinner.setSelection(0)
            } else {
                myToast("No pueden estar los campos vacíos.")
            }
        }

        val dataAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, itemsCategorias)

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // attaching data adapter to spinner
        categoriaSpinner.setAdapter(dataAdapter)

        val dataAdapter2: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, itemsCuentas)

        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // attaching data adapter to spinner
        cuentaSpinner.setAdapter(dataAdapter2)

        verTodosBtn.setOnClickListener {
            val myIntent = Intent(this, RecyclerViewActivity::class.java)
            startActivity(myIntent)
        }
    }

    private fun myToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
