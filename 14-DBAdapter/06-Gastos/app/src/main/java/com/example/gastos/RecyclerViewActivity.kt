package com.example.gastos

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {
    private val db: SQLiteDatabase =
        MainActivity.gastosDBHelper.readableDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        // Modificamos el título de la activity.
        this.title = resources.getString(R.string.app_name)+" [RecyclerView]"
        // Lanzamos la consulta para obtener el cursor.
        val cursor: Cursor = db.rawQuery(
            " SELECT * FROM ${MyDBOpenHelper.TABLE_GASTOS}",
            null
        )

        val cursorCat: Cursor = db.rawQuery(
            "SELECT * FROM ${MyDBOpenHelper.TABLE_CATEGORIAS}",
            null
        )

        val cursorCuentas: Cursor = db.rawQuery(
            "SELECT * FROM ${MyDBOpenHelper.TABLE_CUENTAS}",
            null
        )

        // Creamos el adaptador con el resultado del cursor.
        val myRecyclerViewAdapter = MyRecyclerViewAdapter()
        myRecyclerViewAdapter.MyRecyclerViewAdapter(this, cursor, cursorCat, cursorCuentas)
        // Montamos el RecyclerView.
        myRecycler.setHasFixedSize(true)
        myRecycler.layoutManager = LinearLayoutManager(this)
        myRecycler.adapter = myRecyclerViewAdapter
    }
    override fun onDestroy() {
        super.onDestroy()
        // Cerramos la conexión al terminar la activity.
        Log.d("onDestroy", "Cerramos la conexión")
        db.close()
    }
}