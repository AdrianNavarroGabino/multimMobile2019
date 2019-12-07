package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val subjects = arrayOf(
        "PSP", "EIE", "Mobile devices", "SGE", "Data access", "Interfaces")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter(this, R.layout.listview_item, subjects)

        listView.adapter = adapter

        listView.onItemClickListener =
            object : AdapterView.OnItemClickListener
            {
                override
                fun onItemClick(parent: AdapterView<*>?, view: View?,
                                position: Int, id: Long) {

                    Snackbar.make(
                        root_layout,
                        "Clicked ${listView.getItemAtPosition(position)}",
                        Snackbar.LENGTH_LONG
                    ).setAction(
                        "Accept"
                    ){

                    }.show()
                }
            }
    }
}
