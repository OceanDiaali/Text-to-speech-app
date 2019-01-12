package com.example.ocean.speeker

import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null
    //private var buttonSpeak: Button? = null
    //private var buttonSpeak: ImageView? = this.button_speak
    private var editText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //buttonSpeak = this.button_speak
        editText = this.edittext_input

       //buttonSpeak!!.isEnabled = false;
        tts = TextToSpeech(this, this)

        button_speak.setOnClickListener { speakOut() }

        //buttonSpeak!!.setOnClickListener { speakOut() }
    }

    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                //Log.e("TTS","The Language specified is not supported!")
                Toast.makeText(applicationContext, "This language is not supported!", Toast.LENGTH_LONG).show()
            } else {
                //buttonSpeak!!.isEnabled = true
            }

        } else {
            //Log.e("TTS", "Initilization Failed!")
            Toast.makeText(applicationContext, "Failed to initialize!", Toast.LENGTH_LONG).show()
        }

    }

    private fun speakOut() {
        val text = editText!!.text.toString()
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
    }

    public override fun onDestroy() {
        // Shutdown TTS
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

} // end of class main
