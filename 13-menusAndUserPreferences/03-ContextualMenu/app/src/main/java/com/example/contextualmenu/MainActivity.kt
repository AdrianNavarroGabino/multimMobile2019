package com.example.contextualmenu

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_enter_name.*

class MainActivity : AppCompatActivity() {
    private val lenguajes =
        mutableListOf("C++", "C", "C#",
            "Java", "Python", "Pearl")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Montamos la vista para la lista.
        var arrayAdapter: ArrayAdapter<String> =
            ArrayAdapter(this,
                android.R.layout.simple_expandable_list_item_1,
                lenguajes)
        // Cargamos los datos en la lista.
        listView.adapter = arrayAdapter
        // Registramos el menú contextual al listview.
        registerForContextMenu(listView)
        // Acción sobre el elemento de la lista pulsado.
        listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(
                this,
                "Clicked $id ${listView.getItemAtPosition(position)}",
                Toast.LENGTH_SHORT).show()
        }
    }
    /**
     * "Inflamos" el menú contextual con nuestro resource.
     * se ejecuta tras el registro.
     */
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.context_menu, menu)
    }
    /**
     * Comprobamos la opción de menú seleccionada
     * y sobre que item se ha ejecutado.
     */
    override fun onContextItemSelected(item: MenuItem?): Boolean {
        // Obtenemos el nombre de la persona, con
        // ApaterView.AdapterContextMenuInfo obtenemos la posición
        // sobre la que se ha hecho clic.
        val info = item!!.menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        val nombre = lenguajes[posicion]
        return when (item!!.itemId) {
            R.id.option01 ->{
                lenguajes.removeAt(posicion)
                listView.adapter = ArrayAdapter(this,
                    android.R.layout.simple_expandable_list_item_1,
                    lenguajes)
                return true
            }
            R.id.option02 ->{
                val builder = AlertDialog.Builder(this)
                val inflater = layoutInflater
                builder.setTitle("Renombrar")
                builder.setMessage("Nuevo nombre:")
                builder.setPositiveButton(android.R.string.yes) { dialog, _ ->
                    lenguajes.set(posicion, (dialog as AlertDialog).Nombre.text.toString())
                    listView.adapter = ArrayAdapter(this,
                        android.R.layout.simple_expandable_list_item_1,
                        lenguajes)
                }
                builder.setView(inflater.inflate(R.layout.dialog_enter_name, null))
                builder.show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
