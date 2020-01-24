package com.example.sqlitebasicactivity

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var peopleDBHelper: MyDBOpenHelper
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        peopleDBHelper = MyDBOpenHelper(this, null)

        val db: SQLiteDatabase =
            MainActivity.peopleDBHelper.readableDatabase

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

        fab.setOnClickListener { view ->
            val myIntent = Intent(this, CreateActivity::class.java).apply {

            }
            startActivityForResult(myIntent, 5435)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 5435)
        {
            val db: SQLiteDatabase =
                MainActivity.peopleDBHelper.readableDatabase

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
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
