package com.example.gastos

import android.content.Context
import android.database.Cursor
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class MyRecyclerViewAdapter :
    RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {
    lateinit var context: Context
    private lateinit var cursor: Cursor
    private lateinit var cursorCat: Cursor
    private lateinit var cursorCuentas: Cursor

    fun MyRecyclerViewAdapter(context: Context, cursor: Cursor, cursorCat: Cursor, cursorCuentas: Cursor) {
        this.context = context
        this.cursor = cursor
        this.cursorCat = cursorCat
        this.cursorCuentas = cursorCuentas
    }
    /**
     * Inflamos la vista de los items.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        Log.d("RECYCLERVIEW", "onCreateViewHolder")
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(
                R.layout.item_recycler_view,
                parent,
                false
            )
        )
    }
    override fun getItemCount(): Int {
        return if (cursor != null)
            cursor.count
        else 0
    }
    /**
     * Completamos los datos de cada vista mediante ViewHolder.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Importante para recorrer el cursor.
        cursor.moveToPosition(position)
        Log.d("RECYCLERVIEW", "onBindViewHolder")
        // Asignamos los valores a los elementos de la UI.
        holder._id.text = cursor.getInt(0).toString()
        holder.fecha.text = cursor.getString(1)
        holder.detalles.text = cursor.getString(2)
        holder.importe.text = cursor.getString(3)

        if (cursorCat.moveToFirst()) {
            do {
                if(cursorCat.getInt(0).toString() == cursor.getInt(4).toString())
                {
                    holder.categoria.text = cursorCat.getString(1)
                    break
                }
            } while (cursorCat.moveToNext())
        }

        if (cursorCuentas.moveToFirst()) {
            do {
                if(cursorCuentas.getInt(0).toString() == cursor.getInt(5).toString())
                {
                    holder.cuenta.text = cursorCuentas.getString(1)
                    break
                }
            } while (cursorCuentas.moveToNext())
        }
    }
    inner class ViewHolder : RecyclerView.ViewHolder {
        // Creamos las referencias de la UI.
        val _id: TextView
        val fecha: TextView
        val detalles: TextView
        val importe: TextView
        val categoria: TextView
        val cuenta: TextView
        constructor(view: View) : super(view) {
            this._id = view.findViewById(R.id.tv_rv_id)
            this.fecha = view.findViewById(R.id.tv_rv_fecha)
            this.detalles = view.findViewById(R.id.tv_rv_detalles)
            this.importe = view.findViewById(R.id.tv_rv_importe)
            this.categoria = view.findViewById(R.id.tv_rv_categoria)
            this.cuenta = view.findViewById(R.id.tv_rv_cuenta)
            view.setOnClickListener {
                Toast.makeText(
                    context,
                    "${this._id.text}-${this.fecha.text} ${this.importe.text}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}