package com.example.peterperez.colorsliderm5

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import java.io.FileReader


class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val listView = findViewById<ListView>(R.id.main_listview)
        listView.setBackgroundColor(Color.CYAN)

        listView.adapter = MyCustomAdapter(this)

    }


    private class MyCustomAdapter(context:Context) : BaseAdapter() {
        lateinit var color1:String

        val colors = arrayListOf<String>()

        fun populateColors(){
           var lines=FileReader("colorsaves.txt").readLines()
            lines.forEach{colors.add(it)}
            }

        private val mContext:Context

        init{
            mContext=context
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            populateColors()
            val layoutInflater=LayoutInflater.from(mContext)
            val rowMain = layoutInflater.inflate(R.layout.row_main, viewGroup,false)
            val rowHex = rowMain.findViewById<TextView>(R.id.textView_hex)
            rowHex.text= colors.get(position)
            return rowMain
        }

        override fun getItem(position: Int): Any {
             return "test string"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return colors.size        }

    }
}
