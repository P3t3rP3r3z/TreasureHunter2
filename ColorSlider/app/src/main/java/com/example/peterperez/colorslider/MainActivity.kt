package com.example.peterperez.colorslider

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.SurfaceView
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()  {

    lateinit var rslider: SeekBar
    lateinit var gslider: SeekBar
    lateinit var bslider: SeekBar
    lateinit var value : TextView
    lateinit var surface : SurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rslider = findViewById(R.id.r_slider)
        gslider = findViewById(R.id.g_slider)
        value = findViewById(R.id.textv_result)
        bslider = findViewById(R.id.b_slider)
        surface = findViewById(R.id.surface_view)
        rslider.max=255
        gslider.max=255
        bslider.max=255


        fun updateSurface():Unit {
            var rslide = rslider.getProgress() * 0x10000
            var gslide= gslider.getProgress() * 0x100
            var bslide= bslider.getProgress()
            var status =0xff000000 + rslide + gslide + bslide
            surface.setBackgroundColor(status.toInt())

            reset_button.setOnClickListener{
                surface.setBackgroundColor(0)
                rslider.setProgress(0)
                gslider.setProgress(0)
                bslider.setProgress(0)
            }
        }

        rslider.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                value.text= "Red Value is "+Integer.toHexString(rslider.getProgress())+"\nGreen Value is " +
                        ""+Integer.toHexString(gslider.getProgress())+ "\nBlue Value is" +
                        " "+Integer.toHexString(bslider.getProgress())
                updateSurface()

            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        gslider.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                value.text= "Red Value is "+Integer.toHexString(rslider.getProgress())+"\nGreen Value is " +
                        ""+Integer.toHexString(gslider.getProgress())+ "\nBlue Value is" +
                        " "+Integer.toHexString(bslider.getProgress())
                updateSurface()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        bslider.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                value.text= "Red Value is "+Integer.toHexString(rslider.getProgress())+"\nGreen Value is " +
                        ""+Integer.toHexString(gslider.getProgress())+ "\nBlue Value is" +
                        " "+Integer.toHexString(bslider.getProgress())
                updateSurface()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })


    }
}
