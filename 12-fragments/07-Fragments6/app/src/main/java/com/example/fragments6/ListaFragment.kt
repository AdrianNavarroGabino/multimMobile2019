package com.example.fragments6


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment

/**
 * A simple [Fragment] subclass.
 */
class ListaFragment : ListFragment() {

    public interface ItemSeleccionable {
        fun seHaEscogidoUnItem(posicion: Int)
    }

    var miActivity: ItemSeleccionable? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val nombres = arrayOf("Dato 1", "Dato 2", "Dato 3", "Dato 4", "Dato 5", "Dato 6", "Dato 7", "Dato 8")
        listAdapter = ArrayAdapter<String>(activity!!,
            android.R.layout.simple_list_item_1, nombres)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is ItemSeleccionable) {
            miActivity = context
        } else {
            throw RuntimeException("Error")
        }
    }

    override fun onListItemClick(l: ListView?, v: View?, position:
    Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        miActivity?.seHaEscogidoUnItem(position)
    }
}
