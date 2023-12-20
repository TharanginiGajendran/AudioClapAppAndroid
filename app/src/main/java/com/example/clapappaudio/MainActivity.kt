package com.example.clapappaudio

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    //create object reference variable for media player
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mediaPlayer1: MediaPlayer

    private lateinit var clapButton: Button
    private lateinit var sunButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaPlayer1 = MediaPlayer.create(this,R.raw.sunflower)

        mediaPlayer = MediaPlayer.create(this,R.raw.crowd_cheering)

        viewListeners()
    }

    private fun viewListeners(){

        sunButton = findViewById(R.id.boomBtn)
        clapButton = findViewById(R.id.clapBtn)
        sunButton.setOnClickListener {
            mediaPlayer1.start()
        }

        clapButton.setOnClickListener {
                mediaPlayer.start()
            }

    }
}