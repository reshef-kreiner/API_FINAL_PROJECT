package com.example.api_movie_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.api_movie_app.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cinemaIcon.alpha = 0f
        binding.splashAppName.alpha = 0f

        binding.cinemaIcon.animate().alpha(1f).setDuration(1500).start()
        binding.splashAppName.animate().alpha(1f).setDuration(1500).withEndAction {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
