package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val myAdapter : RecyclerAdapter = RecyclerAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
    }
    private fun setUpRecyclerView(){

        RVcourses.setHasFixedSize(true)

        RVcourses.layoutManager = LinearLayoutManager(this)

        myAdapter.RecyclerAdapter(getCourses(), this)

        RVcourses.adapter = myAdapter
    }
    private fun getCourses(): MutableList<MyCourses> {
        val courses: MutableList<MyCourses> = arrayListOf()
        courses.add(MyCourses("1DAM", "1º Desarrollo de aplicaciones multiplataforma", R.mipmap.cisne))
        courses.add(MyCourses("2DAM", "2º Desarrollo de aplicaciones multiplataforma", R.mipmap.erizo))
        courses.add(MyCourses("1DAW", "1º Desarrollo de aplicaciones web", R.mipmap.gato))
        courses.add( MyCourses("2DAW", "2º Desarrollo de aplicaciones web", R.mipmap.gorrion))
        return courses
    }
}
