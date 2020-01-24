package com.example.sqlitebasicactivity

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class MyDBOpenHelper(context: Context, factory:
SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "MyDatabase.db"
        val TABLE_PEOPLE = "people"
        val COLUMN_NAME = "name"
        val COLUMN_SURNAME = "surname"
    }

    /**
     * Método llamado cuando se crea la base por primera
     * vez. Debe producirse la creación de todas las tablas
     * que formen la base de datos.
     */
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            val createTablePeople = "CREATE TABLE people " +
                    "(_id INTEGER PRIMARY KEY, name TEXT, surname TEXT)"
            db!!.execSQL(createTablePeople)
        } catch (e: SQLiteException) {
            Log.e("SQLite(onCreate)", e.message.toString())
        }
    }

    /**
     * Este método se invocará cuando la base de datos necesite
     * ser actualizada. Se utiliza para hacer DROPs, añadir tablas
     * o cualquier acción que actualice el esquema de la BD.
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion:
    Int) {
        try {
            val dropTablePeople = "DROP TABLE IF EXISTS people"
            db!!.execSQL(dropTablePeople)
            onCreate(db)
        } catch (e: SQLiteException) {
            Log.e("SQLite(onUpgrade)", e.message.toString())
        }
    }

    /**
     * Método opcional.
     * Se llamará a este método después de abrir la base de datos,
     * antes de ello, comprobará si está en modo lectura. Se llama
     * justo después de establecer la conexión y crear el esquema.
     */
    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
        Log.d("onOpen","Database opened!!")
    }

    /**
     * Método para añadir una persona a la tabla.
     */
    fun addPerson(name: String, surname: String) {
        // Creamos un ArrayMap<>().
        val data = ContentValues()
        data.put(COLUMN_NAME, name)
        data.put(COLUMN_SURNAME, surname)
        // Abrimos la BD en modo escritura.
        val db = this.writableDatabase
        db.insert(TABLE_PEOPLE, null, data)
        db.close()
    }

    /**
     * Método para eliminar una persona de la tabla
     * por el nombre.
     */
    fun delPerson(name: String) {
        val args = arrayOf(name)
        // Abrimos la BD en modo escritura.
        val db = this.writableDatabase
        // Se puede elegir un sistema u otro.
        // db.delete(TABLE_PEOPLE, "$COLUMN_NAME=?", args)
        db.execSQL("DELETE FROM $TABLE_PEOPLE WHERE $COLUMN_NAME=?", args)
        db.close()
    }

    /**
     * Método para actualizar el nombre de una persona
     * de la tabla por el nombre.
     */
    fun updatePerson(oldName: String, newName: String) {
        val args = arrayOf(oldName)
        // Creamos un ArrayMap<>().
        val data = ContentValues()
        data.put(COLUMN_NAME, newName)
        val db = this.writableDatabase
        db.update(TABLE_PEOPLE, data, "$COLUMN_NAME=?", args)
        db.close()
    }
}