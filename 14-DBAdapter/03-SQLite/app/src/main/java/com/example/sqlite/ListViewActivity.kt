package com.example.sqlite

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cursoradapter.widget.CursorAdapter
import com.example.sqlite.MainActivity.Companion.peopleDBHelper
import kotlinx.android.synthetic.main.activity_listview.*

class ListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listview)
        val db: SQLiteDatabase = peopleDBHelper.readableDatabase
        val cursor: Cursor = db.rawQuery(
            " SELECT * FROM ${MyDBOpenHelper.TABLE_PEOPLE}",
            null
        )
        // Creamos el CursorAdapter.
        val myCursorAdapter = MyListCursorAdapter(this, cursor)
        // Cargamos los datos en el ListView.
        myListView.adapter = myCursorAdapter
        db.close()
    }
    inner class MyListCursorAdapter(context: Context, cursor: Cursor) :
        CursorAdapter(context, cursor, FLAG_REGISTER_CONTENT_OBSERVER) {
        /**
         * Infla cada elemento de la lista.
         */
        override fun newView(p0: Context, p1: Cursor, p2: ViewGroup): View {
            val inflater = LayoutInflater.from(p0)
            return inflater.inflate(R.layout.item_listview, p2, false)
        }
        /**
         * Rellena el ListView.
         */
        override fun bindView(p0: View, p1: Context, p2: Cursor) {
            val name: TextView = p0.findViewById(R.id.tv_item_name)
            val surname : TextView = p0.findViewById(R.id.tv_item_surname)
            name.text = p2.getString(1)
            surname.text = p2.getString(2)
            p0.setOnClickListener {
                Toast.makeText(
                    applicationContext,
                    "${name.text} ${surname.text}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}