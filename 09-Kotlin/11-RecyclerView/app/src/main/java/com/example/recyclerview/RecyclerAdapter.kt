package com.example.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    // Variables internes de la clase
    var courses: MutableList<MyCourses> = ArrayList()
    lateinit var context: Context
    // Constructor de la clase
    fun RecyclerAdapter(courses: MutableList<MyCourses>, context: Context) {
        this.courses = courses
        this.context = context
    }
    // Este método se encarga de pasar los objetos al ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = courses.get(position)
        holder.bind(item, context)
    }
    // Es el encargado de devolver el ViewHolder ya configurado
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            layoutInflater.inflate(
                R.layout.item_courses_list, parent, false
            )
        )
    }
    // Devuelve el tamaño del array
    override fun getItemCount(): Int {
        return courses.size
    }
    // Esta clase se encarga de rellenar cada una de las vistas que
    // se inflarán en el RecyclerView
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Aquí es necesario utilizar findViewById para localizar el elemento
        // de la vista que se pasa como parámetro
        private val name = view.findViewById(
            R.id.name) as TextView
        private val abrev = view.findViewById(
            R.id.abrev) as TextView
        private val courseImage = view.findViewById(
            R.id.courseImage) as ImageView
        fun bind(course:MyCourses, context: Context){
            name.text = course.abrev
            abrev.text = course.name
            courseImage.setImageResource(course.image!!)
            itemView.setOnClickListener {
                Toast.makeText(
                    context,
                    course.abrev,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}