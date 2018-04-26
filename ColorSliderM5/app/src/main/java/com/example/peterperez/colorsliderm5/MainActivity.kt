package com.example.peterperez.colorsliderm5

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.SurfaceView
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    lateinit var rslider: SeekBar
    lateinit var gslider: SeekBar
    lateinit var bslider: SeekBar
    lateinit var value : TextView
    lateinit var surface : SurfaceView
    lateinit var rval : EditText
    lateinit var bval : EditText
    lateinit var gval : EditText
    lateinit var tosave : String



    override fun onCreate(savedInstanceState: Bundle?) {
        setSupportActionBar(findViewById(R.id.my_toolbar))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        var actbar=my_toolbar
        rslider = r_slider
        gslider = g_slider
        value = textv_result
        bslider = b_slider
        surface = surface_view
        rval = redval
        bval = blueval
        gval = greenval
        rslider.max=255
        gslider.max=255
        bslider.max=255
        actbar.setLogo(R.drawable.ic_launcher_foreground)
        var nametosave = name
        val filesDir = getFilesDir()
        val letDirectory = File(filesDir, "LET")
        letDirectory.mkdirs()
        val file = File(letDirectory, "Colors.txt")
        val pr = file.printWriter()


        fun updateSurface() {
            var rslide = rslider.getProgress()
            var gslide= gslider.getProgress()
            var bslide= bslider.getProgress()

            var status =0xff000000 + rslide*0x10000 + gslide*0x100 + bslide
            value.text= Integer.toHexString(status.toInt())
            surface.setBackgroundColor(status.toInt())
            tosave = status.toString() + nametosave

            reset_button.setOnClickListener{
                surface.setBackgroundColor(0)
                rslider.setProgress(0)
                gslider.setProgress(0)
                bslider.setProgress(0)
            }
            button_save.setOnClickListener{
                file.appendText(tosave)
                Toast.makeText(this, "SAVED", Toast.LENGTH_SHORT).show()
            }
        }

        nametosave.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                //name.setSelection(name.getText().length)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                nametosave.setSelection(nametosave.getText().length)
            }

        })

        rslider.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                rval.setText(rslider.getProgress().toString())
                updateSurface()

            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        gslider.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                gval.setText(gslider.getProgress().toString())
                updateSurface()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        bslider.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    bval.setText(bslider.getProgress().toString())
                updateSurface()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        rval.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                rval.setSelection(rval.getText().length)
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                rval.setSelection(rval.getText().length)
                try{rslider.setProgress(Integer.parseInt(rval.getText().toString()))
                    updateSurface()}
                catch(ex : Exception){
                    value.text="invalid value"
                    rslider.setProgress(0)
                }
            }
        })

        bval.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                bval.setSelection(bval.getText().length)
                try{bslider.setProgress(Integer.parseInt(bval.getText().toString()))
                    updateSurface()}
                catch(ex : Exception){
                    value.text="invalid value"
                    bslider.setProgress(0)
                }
            }
        })

        gval.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                gslider.setProgress(Integer.parseInt(gval.getText().toString()))
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                gval.setSelection(gval.getText().length)
                try{gslider.setProgress(Integer.parseInt(gval.getText().toString()))
                    updateSurface()}
                catch(ex : Exception){
                    value.text="invalid value"
                    gslider.setProgress(0)
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_load-> {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }


}