package com.example.actionmode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var actionMode: ActionMode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView.setOnLongClickListener {
            when (actionMode) {
                null -> {
                    // Lanzamos el ActionMode.
                    actionMode = it.startActionMode(actionModeCallback)
                    it.isSelected = true
                    true
                } else -> false
            }
        }
    }

    private val actionModeCallback = object : ActionMode.Callback {
        /**
         * Se llama cuando el usuario selecciona un elemento del menú.
         */
        override fun onActionItemClicked(
            mode: ActionMode?, item: MenuItem?): Boolean {
            return when (item!!.itemId) {
                R.id.option01 -> {
                    Toast.makeText(
                        applicationContext,
                        "Option 1",
                        Toast.LENGTH_LONG
                    ).show()
                    return true
                }
                R.id.option02 -> {
                    Toast.makeText(
                        applicationContext,
                        "Option 2",
                        Toast.LENGTH_LONG
                    ).show()
                    return true
                }
                else -> false
            }
        }
        /**
         * Se llama cuando se crea el modo acción mediante
         * la llamada de startActionMode().
         */
        override fun onCreateActionMode(
            mode: ActionMode?, menu: Menu?): Boolean {
            val inflater = menuInflater
            inflater.inflate(R.menu.menu_image, menu)
            return true
        }
        /**
         * Se llama cada vez que el modo acción se muestra, siempre
         * después de onCreateActionMode().
         */
        override fun onPrepareActionMode(
            mode: ActionMode?, menu: Menu?): Boolean {
            return false
        }
        /**
         * Se llama cuando el usuario sale del modo de acción.
         */
        override fun onDestroyActionMode(mode: ActionMode?) {
            actionMode = null
        }
    }
}
