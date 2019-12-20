package com.adrian.manythreads

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        t1Btn.setOnClickListener {
            createThread("Thread 1", 500, 10, t1TV)
        }

        t2Btn.setOnClickListener {
            createThread("Thread 2", 100, 20, t2TV)
        }

        t3Btn.setOnClickListener {
            createThread("Thread 3", 200, 30, t3TV)
        }

        t4Btn.setOnClickListener {
            createThread("Thread 4", 300, 40, t4TV)
        }

    }

    private fun createThread(mensaje: String, duracion: Long,
                          vueltas: Int, visualizador: TextView
    ) {
        visualizador.text = "0"
        visualizador.setBackgroundColor(Color.TRANSPARENT)
        Thread(
            // Imitamos una tarea.
            Runnable {
                var contador = 0
                while (contador < vueltas) {
                    // Operación a realizar
                    try {
                        contador++
                        Thread.sleep(duracion)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    // Nos permite actuar con elementos de la UI.
                    visualizador.post(Runnable {
                        visualizador.text = contador.toString()
                    })
                }
                // Acciones que se realizarán al finalizar el hilo.
                runOnUiThread {
                    visualizador.text = "FIN"
                    visualizador.setBackgroundColor(Color.GREEN)
                    Toast.makeText(
                        this,
                        mensaje,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        ).start()
    }

}
