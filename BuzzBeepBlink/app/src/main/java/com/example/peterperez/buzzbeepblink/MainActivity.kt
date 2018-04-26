package com.example.peterperez.buzzbeepblink
import android.content.Context
import android.hardware.camera2.CameraManager
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.os.Vibrator
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        var counter:Int=0
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_buzz.setOnClickListener{
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(500)
        }

        button_beep.setOnClickListener{
            val tone = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
            tone.startTone(ToneGenerator.TONE_DTMF_3,600)
        }

        button_blink.setOnClickListener{
            val manager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
            var camlist= manager.getCameraIdList()
            if(camlist[0]!=null){
                if(counter %2 !=1){
                    var cam= camlist[0]
                    manager.setTorchMode(cam,true)}
                if(counter %2 !=0){
                    var cam= camlist[0]
                    manager.setTorchMode(cam,false)}
                counter++

            }

        }
    }
}
