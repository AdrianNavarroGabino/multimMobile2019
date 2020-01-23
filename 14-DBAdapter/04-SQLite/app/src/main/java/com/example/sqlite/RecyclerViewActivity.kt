package com.example.sqlite

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recyclerview.*

class RecyclerViewActivity : AppCompatActivity() {
    private val db: SQLiteDatabase =
        MainActivity.peopleDBHelper.readableDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)
        // Modificamos el título de la activity.
        this.title = resources.getString(R.string.app_name)+" [RecyclerView]"
        // Lanzamos la consulta para obtener el cursor.
        val cursor: Cursor = db.rawQuery(
            " SELECT * FROM ${MyDBOpenHelper.TABLE_PEOPLE}",
            null
        )
        // Creamos el adaptador con el resultado del cursor.
        val myRecyclerViewAdapter = MyRecyclerViewAdapter()
        myRecyclerViewAdapter.MyRecyclerViewAdapter(this, cursor)
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