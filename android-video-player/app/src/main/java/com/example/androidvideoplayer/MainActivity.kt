package com.example.androidvideoplayer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var urlInput: EditText
    private lateinit var playButton: ImageButton
    private lateinit var errorText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        urlInput = findViewById(R.id.urlInput)
        playButton = findViewById(R.id.playButton)
        errorText = findViewById(R.id.errorText)

        playButton.setOnClickListener {
            val url = urlInput.text.toString().trim()
            if (validateUrl(url)) {
                errorText.visibility = TextView.GONE
                openPlayer(url)
            } else {
                errorText.text = "Please enter a valid video URL"
                errorText.visibility = TextView.VISIBLE
            }
        }

        urlInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                playButton.performClick()
                true
            } else {
                false
            }
        }
    }

    private fun validateUrl(url: String): Boolean {
        if (url.isEmpty()) return false
        return Patterns.WEB_URL.matcher(url).matches()
    }

    private fun openPlayer(url: String) {
        val intent = Intent(this, PlayerActivity::class.java)
        intent.putExtra("video_url", url)
        startActivity(intent)
    }
}
