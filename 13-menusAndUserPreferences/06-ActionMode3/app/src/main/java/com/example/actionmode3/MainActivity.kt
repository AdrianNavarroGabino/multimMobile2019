package com.example.actionmode3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast

class MainActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showPopup(v: View) {
        PopupMenu(this, v).apply {
            setOnMenuItemClickListener(this@MainActivity)
            inflate(R.menu.actions)
        }.show()
    }
    // Recogemos la opción seleccionada por el usuario.
    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            R.id.menu_archive -> {
                Toast.makeText(this,
                    "Opción Archivar",
                    Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_delete -> {
                Toast.makeText(this,
                    "Opción Eliminar",
                    Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_save -> {
                Toast.makeText(this,
                    "Opción Guardar",
                    Toast.LENGTH_SHORT).show()
                true
            }
            else -> false
        }
    }
}
