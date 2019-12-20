package com.adrian.asynctasks

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lateinit var myAsyncTask1: MyAsyncTask
        lateinit var myAsyncTask2: MyAsyncTask
        task1Btn.setOnClickListener {
            it.isEnabled = false
            myAsyncTask1 = MyAsyncTask(this, progressBar1, task1Btn)
            // Ejecutamos la tarea.
            myAsyncTask1.execute(100, 20)
        }
        cancelBtn.setOnClickListener {
            task1Btn.isEnabled = true
            myAsyncTask1.cancel(true)
        }
        task2Btn.setOnClickListener {
            it.isEnabled = false
            myAsyncTask2 = MyAsyncTask(this, progressBar2, task2Btn)
            // Ejecutamos la tarea.
            myAsyncTask2.execute(200, 15)
        }
        cancelBtn2.setOnClickListener {
            task2Btn.isEnabled = true
            myAsyncTask2.cancel(true)
        }

    }

    private inner class MyAsyncTask(
        val contexto: Context,
        val progressBar: ProgressBar,
        val button: Button): AsyncTask<Int, Int, Int>() {
        /**
         * Acciones antes de iniciar la tarea, utilizada
         * para inicializar o configurar la tarea.
         */
        override fun onPreExecute() {
            super.onPreExecute()
            progressBar.progress = 0
        }
        /**
         * La tarea que queremos hacer en cuestión, vararg
         * viene a ser un array con los parámetros indicados.
         */
        override fun doInBackground(vararg p0: Int?): Int {
            if (p0.size == 2) {
                var contador = 0
                while (contador < p0[0]!!) {
                    try {
                        contador++
                        Thread.sleep(p0[1]!!.toLong())
                    } catch (e: Exception) {
                        Log.println(
                            Log.WARN,
                            "doInBackground",
                            e.message.toString()
                        )
                    }
                    // Comprobamos si la tarea ha sido cancelada.
                    if (!isCancelled)
                        publishProgress((
                                ((contador+1)*100/p0[0]!!).toFloat()
                                ).toInt())
                    else break
                }
                return 1
            } else return -1
        }
        /**
         * Se ejecuta cuando la tarea es cancelada.
         */
        override fun onCancelled(result: Int?) {
            super.onCancelled(result)
            progressBar.progress = 0
            Toast.makeText(
                contexto,
                "Task has been canceled!!",
                Toast.LENGTH_SHORT
            ).show()
        }
        /**
         * Nos permite mostrar información al usuario, se ejecuta
         * cuando se utiliza el método publishProgress() desde el
         * método doInBackground().
         */
        override fun onProgressUpdate(vararg values: Int?) {
            progressBar.progress = values[0]!!
            super.onProgressUpdate(values[0])
        }
        /**
         * Se ejecuta al finalizar el método doInBackground()
         * y se le pasa el resultado obtenido.
         */
        override fun onPostExecute(result: Int?) {
            super.onPostExecute(result)
            if (result == 1) {
                progressBar.progress = 100
                Toast.makeText(
                    contexto,
                    "Task has finished!!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            button.isEnabled = true
        }
    }
}


