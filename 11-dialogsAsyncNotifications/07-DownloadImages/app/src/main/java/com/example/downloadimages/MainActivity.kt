package com.example.downloadimages

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val asyncTask = MyAsyncTask(this, linear, progressBar, tv_info)
        btn_start.setOnClickListener {
            asyncTask.execute(
                "https://www.jardineriaon.com/wp-content/uploads/2018/04/floresdecorativas-y-vistosas.jpg",
                "https://knowi.es/wp-content/uploads/2015/03/shutterstock_165277220-1000x640.jpg",
                "https://www.mundoflores.net/imagenes/significado-color-flores735x450.jpg",
                "https://www.ecoticias.com/userfiles/extra/RMPM_56.jpg",
                "https://www.ecoticias.com/userfiles/extra/MSHO_floresesall.jpg",
                "https://www.floresyplantas.net/wp-content/uploads/flores-deachillea-millefolium.jpg",
                "https://decoratrix.com/img/posts/2018/01/flores-de-inviernoamaryllis.jpg"
            )
            it.isEnabled = false
            btn_cancel.isEnabled = true
        }
        btn_cancel.setOnClickListener {
            asyncTask.cancel(true)
            it.isEnabled = false
        }
    }

    private inner class MyAsyncTask(val contexto: Context,
                                    val content: LinearLayout,
                                    val progressDownload: ProgressBar,
                                    val textinfo: TextView
    )
        : AsyncTask<String, Int, MutableList<Bitmap>>() {
        /**
         * Preparación antes de la tarea.
         */
        override fun onPreExecute() {
            super.onPreExecute()
            progressDownload.progress = 0
            textinfo.text = "The download has started"
        }

        /**
         * Método encargado de la descarga de las imágenes.
         */
        override fun doInBackground(vararg p0: String?): MutableList<Bitmap> {
            // lista para almacenar las imágenes.
            val list = ArrayList<Bitmap>()
            var cont = 0
            for (item in p0) {
                val urlImage = URL(item)
                try {
                    val input = urlImage.openStream()
                    list.add(BitmapFactory.decodeStream(input))
                } catch (e: Exception) {
                    Log.println(Log.WARN, "doInBackground", e.message!!)
                }
                if (!isCancelled)
                    publishProgress(((++cont * 100)) / p0.size)
                else break
            }
            return list
        }

        /**
         * Mostrar información al usuario. Se ejecutará cuando desde el
         * método doInBackground() se ejecute el método publishProgress().
         */
        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(values[0])
            progressDownload.progress = values[0]!!
            textinfo.text = "Descarga ${values[0]}%"
        }

        /**
         * Ejecutamos al finalizar la tarea en doInBackground().
         */
        override fun onPostExecute(result: MutableList<Bitmap>?) {
            super.onPostExecute(result)
            textinfo.text = "Download completed\n ${result?.size} " +
                    "files have been downloaded"
            for (image in result!!) {
                content.addView(addImage(image))
            }
        }

        /**
         * En caso de cancelación de la tarea.
         */
        override fun onCancelled(result: MutableList<Bitmap>?) {
            super.onCancelled(result)
            textinfo.text = "The download has been canceled\n ${result?.size} " +
                    "files have been downloaded"
            for (image in result!!) {
                content.addView(addImage(image))
            }
        }

        /**
         * Método encargado de inflar nuestro LinearLayout
         * con los ImageView.
         */
        private fun addImage(bitmap: Bitmap): ImageView {
            // Ajustamos la anchura y la altura para el ImageView.
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                400
            )
            // Margen inferior.
            params.bottomMargin = 20
            // Creamos un ImageView asignando el contexto.
            val imageView = ImageView(contexto)
            // Asignamos los parámetros al IV.
            imageView.layoutParams = params
            // Asignamos la imagen y un fondo.
            imageView.run {
                setImageBitmap(bitmap)
                setBackgroundResource(R.color.colorPrimary)
            }
            return imageView
        }
    }
}
