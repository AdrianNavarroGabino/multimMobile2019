package com.example.baseadapter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.gridview_item.view.*
import layout.MyItems

class MainActivity : AppCompatActivity() {
    var adapter: ItemAdapter? = null
    var itemsList = ArrayList<MyItems>()


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val subjectsName = resources.getStringArray(R.array.name)
        val subjectsName2 = resources.getStringArray(R.array.name2)
        val subjectsDescription = resources.getStringArray(R.array.description)

        for(i in 0..(subjectsName.size-1))
        {
            itemsList.add(MyItems(subjectsName[i], subjectsName2[i], subjectsDescription[i]))
        }

        adapter = ItemAdapter(this, itemsList)

        myGridView.adapter = adapter
    }

    class ItemAdapter : BaseAdapter {
        var itemsList = ArrayList<MyItems>()
        var context: Context? = null

        constructor(context: Context,
                    itemsList: ArrayList<MyItems>) : super() {
            this.context = context
            this.itemsList = itemsList
        }

        override
        fun getCount(): Int {
            return itemsList.size
        }

        override
        fun getItem(position: Int): Any {
            return itemsList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int,
                             convertView: View?, parent: ViewGroup?): View {
            val item = this.itemsList[position]
            var inflator = context!!.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var itemView = inflator.inflate(R.layout.gridview_item, null)
            itemView.subjectName.text = item.name
            itemView.subjectName2.text = item.name2!!
            itemView.subjectDescription.text = item.description!!

            return itemView
        }
    }
}
