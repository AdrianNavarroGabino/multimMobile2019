package com.example.gastos

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
        val DATABASE_VERSION = 3
        val DATABASE_NAME = "MyDatabase.db"
        val TABLE_GASTOS = "gastos"
        val COLUMN_FECHA = "fecha"
        val COLUMN_DETALLES = "detalles"
        val COLUMN_IMPORTE = "importe"
        val COLUMN_CATEGORIA = "categoria"
        val COLUMN_CUENTA = "cuenta"
        val TABLE_CATEGORIAS = "categorias"
        val TABLE_CUENTAS = "cuentas"
    }

    /**
     * Método llamado cuando se crea la base por primera
     * vez. Debe producirse la creación de todas las tablas
     * que formen la base de datos.
     */
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            val createTableGastos = "CREATE TABLE gastos " +
                    "(_id INTEGER PRIMARY KEY, fecha TEXT, detalles TEXT, importe REAL, categoria INTEGER, cuenta INTEGER)"

            val createTableCategorias = "CREATE TABLE categorias " +
                    "(_id INTEGER PRIMARY KEY, categoria TEXT)"
            val insertCategoria1 = "INSERT INTO categorias (categoria) VALUES('ocio')"
            val insertCategoria2 = "INSERT INTO categorias (categoria) VALUES('gasolina')"
            val createTableCuentas = "CREATE TABLE cuentas " +
                    "(_id INTEGER PRIMARY KEY, cuenta TEXT)"
            val insertCuenta1 = "INSERT INTO cuentas (cuenta) VALUES('casa')"
            val insertCuenta2 = "INSERT INTO cuentas (cuenta) VALUES('trabajo')"
            val insertCuenta3 = "INSERT INTO cuentas (cuenta) VALUES('segunda')"

            db!!.execSQL(createTableCategorias)
            db!!.execSQL(createTableCuentas)
            db!!.execSQL(createTableGastos)
            db!!.execSQL(insertCategoria1)
            db!!.execSQL(insertCategoria2)
            db!!.execSQL(insertCuenta1)
            db!!.execSQL(insertCuenta2)
            db!!.execSQL(insertCuenta3)

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
            val dropTablePeople = "DROP TABLE IF EXISTS gastos"
            val dropTableCategorias = "DROP TABLE IF EXISTS categorias"
            val dropTableCuentas = "DROP TABLE IF EXISTS cuentas"
            db!!.execSQL(dropTablePeople)
            db!!.execSQL(dropTableCategorias)
            db!!.execSQL(dropTableCuentas)
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
    fun addGasto(fecha: String, detalles: String, importe: Double, categoria: Int, cuenta: Int) {
        // Creamos un ArrayMap<>().
        val data = ContentValues()
        data.put(COLUMN_FECHA, fecha)
        data.put(COLUMN_DETALLES, detalles)
        data.put(COLUMN_IMPORTE, importe)
        data.put(COLUMN_CATEGORIA, categoria)
        data.put(COLUMN_CUENTA, cuenta)
        // Abrimos la BD en modo escritura.
        val db = this.writableDatabase
        db.insert(TABLE_GASTOS, null, data)
        db.close()
    }

    /**
     * Método para eliminar una persona de la tabla
     * por el nombre.
     */
    /*fun delGasto(name: String) {
        val args = arrayOf(name)
        // Abrimos la BD en modo escritura.
        val db = this.writableDatabase
        // Se puede elegir un sistema u otro.
        // db.delete(TABLE_PEOPLE, "$COLUMN_NAME=?", args)
        db.execSQL("DELETE FROM $TABLE_GASTOS WHERE $COLUMN_NAME=?", args)
        db.close()
    }*/

    /**
     * Método para actualizar el nombre de una persona
     * de la tabla por el nombre.
     */
    /*fun updateGasto(oldName: String, newName: String) {
        val args = arrayOf(oldName)
        // Creamos un ArrayMap<>().
        val data = ContentValues()
        data.put(COLUMN_NAME, newName)
        val db = this.writableDatabase
        db.update(TABLE_PEOPLE, data, "$COLUMN_NAME=?", args)
        db.close()
    }*/
}