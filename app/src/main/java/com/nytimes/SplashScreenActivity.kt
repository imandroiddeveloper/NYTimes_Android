package com.nytimes

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.nytimes.homeModule.ui.activity.HomeScreenActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFullWindowView()
        setContentView(R.layout.activity_splashscreen)
        holdScreenTimer()
    }

    private fun addFullWindowView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    private fun holdScreenTimer() {
        val timer = object : CountDownTimer(2000, 1000) {
            override fun onTick(tick: Long) {}
            override fun onFinish() {
                moveToNextActivity(this@SplashScreenActivity);
            }
        }
        timer.start()
    }



    private fun moveToNextActivity(context: Context) {
        var intent = Intent(context, HomeScreenActivity::class.java)
        startActivity(intent)
        finish()
    }
}