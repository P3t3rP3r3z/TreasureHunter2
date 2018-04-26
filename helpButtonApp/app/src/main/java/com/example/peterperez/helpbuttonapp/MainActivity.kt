package com.example.peterperez.helpbuttonapp

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ToggleButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        var hideButton = findViewById<ToggleButton>(R.id.hideButton)
        var colorButton = findViewById<ToggleButton>(R.id.colorButton)

        hideButton?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                textView.setVisibility(View.INVISIBLE)
            }
            if (!isChecked) {
                textView.setVisibility(View.VISIBLE)
            }
        }

        colorButton?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                textView.setBackgroundColor(android.graphics.Color.MAGENTA)
            }
            if (!isChecked) {
                textView.setBackgroundColor(Color.parseColor("#8adaf0"))
            }
        }

      }

      override fun onCreateOptionsMenu(menu: Menu): Boolean {
            // Inflate the menu; this adds items to the action bar if it is present.
            menuInflater.inflate(R.menu.menu_main, menu)
            return true
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.helpbutton -> {
                textView.text = "This is My App. There are many like it, but this one is mine. It is designed " +
                        "to demonstrate this message. It also has two toggle buttons. One button will hide this text" +
                        ", the other button will change the color. Care to guess which is which?"
                return true
            }
          }
        return true
       }
    }

