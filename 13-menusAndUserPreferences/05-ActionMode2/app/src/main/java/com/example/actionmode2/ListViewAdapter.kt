package com.example.actionmode2

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView

class ListViewAdapter(context: Context, var contacts: List<String>) :
    BaseAdapter() {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
                as LayoutInflater
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.item_layout_xml, p2, false)
        // Asignamos el nombre al TextView.
        val name = rowView.findViewById(R.id.contact_name) as TextView
        name.text = getItem(p0)
        // Asignamos como etiqueta del checkBox la posición en la que
        // se encuentra.
        val check = rowView.findViewById(R.id.checkBox) as CheckBox
        check.tag = p0
        if (MainActivity.isActionMode) {
            check.visibility = View.VISIBLE
        } else {
            check.visibility = View.GONE
        }
        // Controlamos la selección del usuario mediante la
        // lista seleccion.
        check.setOnCheckedChangeListener { compoundButton, _ ->
            val position = compoundButton.tag as Int
            Log.d("CHECKBOX", position.toString())
            // Añadimos o eliminamos de la lista la selección.
            if (MainActivity.seleccion.contains(contacts[position])) {
                MainActivity.seleccion.remove(contacts[position])
            } else {
                MainActivity.seleccion.add(contacts[position])
            }
            MainActivity.actionMode!!.title =
                "${MainActivity.seleccion.size} items seleccionados"
        }
        // Devolvemos la fila.
        return rowView
    }

    override fun getItem(p0: Int): String {
        return contacts[p0]
    }
    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }
    override fun getCount(): Int {
        return contacts.size
    }
    fun removeContacts(items: List<String>) {
        // Eliminamos los elementos seleccionados.
        for (item in items) {
            MainActivity.personas.remove(item)
        }
        // Notificamos un cambio en la información
        // mostrada en la lista.
        notifyDataSetChanged()
    }
}