package com.example.clapappaudio

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextClock
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    //reference var for media player
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var seekBar: SeekBar
    private lateinit var startTime: TextView
    private lateinit var endTime: TextView

    //runnable -> is a interface, intended to be used by class,
    // whose instances are intended to be executed by a separate thread
    private lateinit var runnable: Runnable
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler = Handler(Looper.getMainLooper())
        //initialize media player in on create

        viewListeners()
    }

    private fun viewListeners() {
       val btn1 = findViewById<FloatingActionButton>(R.id.playBtn)

        btn1.setOnClickListener {
            if (mediaPlayer == null){
                mediaPlayer = MediaPlayer.create(this,R.raw.sunflower)
                initializeSeekBar()

            }
            mediaPlayer?.start()
        }
      val btn2 = findViewById<FloatingActionButton>(R.id.pauseBtn)
        btn2.setOnClickListener {
            mediaPlayer?.pause()
        }
       val btn3 = findViewById<FloatingActionButton>(R.id.stopBtn)
        btn3.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer?.reset()
            mediaPlayer?.release()
            mediaPlayer = null

            handler.removeCallbacks(runnable)
            seekBar.progress = 0

            endTime.text = "0 sec"
            startTime.text = "0 sec"

        }

    }

    private fun initializeSeekBar(){
        seekBar = findViewById(R.id.seekBar)

        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener{
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onProgressChanged(p0: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser){
                    mediaPlayer?.seekTo(progress)
                }


            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onStartTrackingTouch(p0: SeekBar?) {



            }

            override fun onStopTrackingTouch(p0: SeekBar?) {


            }

        })

       seekBar.max  = mediaPlayer!!.duration
        runnable = Runnable {
            seekBar.progress = mediaPlayer!!.currentPosition
            handler.postDelayed(runnable,1000)

             startTime = findViewById<TextView>(R.id.tvPlayed)
            val showTime = mediaPlayer?.currentPosition!! / 1000
            startTime.text = "$showTime sec"


             endTime = findViewById<TextView>(R.id.tvDueTime)
            val end = mediaPlayer?.duration!! /1000
            val dueTime = end-showTime
            endTime.text = "$dueTime sec"


        }
        handler.postDelayed(runnable,1000)

    }
}