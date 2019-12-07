package com.example.spinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.array_subjects,
            android.R.layout.simple_spinner_item
        )
        // Se especifica el diseño que debe utilizar para mostrar la lista
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        // Cargamos el adapter en el spinner
        mySpinner.adapter = adapter
        mySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onNothingSelected(p0: AdapterView<*>?) {}

                override fun onItemSelected(
                    p0: AdapterView<*>?, p1: View?,
                    p2: Int, p3: Long
                ) {
                    if (p2 == 0)
                        Snackbar.make(
                            root_layout,
                            "Not selected!",
                            Snackbar.LENGTH_LONG
                        ).setAction(
                            "Accept"
                        ){

                        }.show()
                    else
                        Snackbar.make(
                            root_layout,
                            "Selected ${mySpinner.getItemAtPosition(p2)}!",
                            Snackbar.LENGTH_LONG
                        ).setAction(
                            "Accept"
                        ){

                        }.show()
                }
            }
    }
}
