package com.example.peterperez.colorpicker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.SurfaceView
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.content_main.*
import java.io.File
import java.util.*

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
    var FNAME = "Colors1.txt"
    val colorlist = mutableListOf<String>()
    // lateinit var nametosave : nameET
//    val fin = File(filesDir, FNAME)
//    val sc = Scanner(fin)





    override fun onCreate(savedInstanceState: Bundle?) {
        setSupportActionBar(findViewById(R.id.my_toolbar))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        var actbar=my_toolbar
        var nametosave = nameET
        filesDir.mkdirs()
        val fin = File(filesDir, FNAME)
        //val inputStream: InputStream = File(FNAME).inputStream()
        val sc = Scanner(fin)
        //val sc = Scanner(File(filesDir, FNAME))
        //val spinner = findViewById<Spinner>(R.id.spinner)

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


        fun updateSurface() {
            var rslide = rslider.getProgress()
            var gslide = gslider.getProgress()
            var bslide = bslider.getProgress()

            var status = 0xff000000 + rslide * 0x10000 + gslide * 0x100 + bslide
            value.text = Integer.toHexString(status.toInt())
            surface.setBackgroundColor(status.toInt())
            tosave = rslide.toString() + " " + gslide.toString() + " " + bslide.toString() + " " + nametosave.text.toString() + "\n"

//            reset_button.setOnClickListener {
//                surface.setBackgroundColor(0)
//                rslider.setProgress(0)
//                gslider.setProgress(0)
//                bslider.setProgress(0)
//            }
        }




        rslider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                rval.setText(rslider.getProgress().toString())
                updateSurface()

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        gslider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                gval.setText(gslider.getProgress().toString())
                updateSurface()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        bslider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
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
                try {
                    rslider.setProgress(Integer.parseInt(rval.getText().toString()))
                    updateSurface()
                } catch (ex: Exception) {
                    value.text = "invalid value"
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
                try {
                    bslider.setProgress(Integer.parseInt(bval.getText().toString()))
                    updateSurface()
                } catch (ex: Exception) {
                    value.text = "invalid value"
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
                try {
                    gslider.setProgress(Integer.parseInt(gval.getText().toString()))
                    updateSurface()
                } catch (ex: Exception) {
                    value.text = "invalid value"
                    gslider.setProgress(0)
                }
            }
        })


        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            // val colorlist = mutableListOf<String>()
//            inputStream.bufferedReader().useLines { lines -> lines.forEach { colorlist.add(it)} }
//            colorlist.forEach{println(">  " + it)}
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, colorlist)
            spinner.adapter = arrayAdapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    val selectedItem = parent.getItemAtPosition(position).toString()

                    Toast.makeText(this@MainActivity, "$selectedItem", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }

            }
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var nametosave = nameET
        //filesDir.mkdirs()
        val fin = File(filesDir, FNAME)
        val sc = Scanner(fin)
        nametosave.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                nameET.setSelection(nameET.getText().length)
                while (sc.hasNextLine()) {
                    colorlist.add(sc.nextLine())
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                nametosave.setSelection(nametosave.getText().length)
                while (sc.hasNextLine()) {
                    colorlist.add(sc.nextLine())
                }
            }

        })
        tosave = nametosave.text.toString() + rslider.progress.toString() + " " + gslider.progress.toString() + " " + bslider.progress.toString()  +  "\n"

        filesDir.mkdirs()
        val f = File(filesDir, FNAME)
        if (f.exists()) {
            f.appendText(tosave)
        } else {
            val out = f.printWriter()
            out.println(tosave)
            out.close()
        }

        Toast.makeText(this, "SAVED", Toast.LENGTH_SHORT).show()

        return true
    }

}