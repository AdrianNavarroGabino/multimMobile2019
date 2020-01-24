package com.example.sqlitebasicactivity

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
    fun MyRecyclerViewAdapter(context: Context, cursor: Cursor) {
        this.context = context
        this.cursor = cursor
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        Log.d("RECYCLERVIEW", "onCreateViewHolder")
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(
                R.layout.item_recyclerview,
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
        holder.id.text = cursor.getString(0)
        holder.name.text = cursor.getString(1)
        holder.surname.text = cursor.getString(2)
    }
    inner class ViewHolder : RecyclerView.ViewHolder {
        // Creamos las referencias de la UI.
        val id: TextView
        val name: TextView
        val surname: TextView
        constructor(view: View) : super(view) {
            this.id = view.findViewById(R.id.tv_rv_id)
            this.name = view.findViewById(R.id.tv_rv_name)
            this.surname = view.findViewById(R.id.tv_rv_surname)
            view.setOnClickListener {
                Toast.makeText(
                    context,
                    "${this.id.text}-${this.name.text} ${this.surname.text}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}