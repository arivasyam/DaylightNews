package com.example.daylightnews.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.daylightnews.R
import com.example.daylightnews.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        var textanim : Animation = AnimationUtils.loadAnimation(this,R.anim.text_splash_animation)
        var logoanim : Animation = AnimationUtils.loadAnimation(this,R.anim.logo_splash_animation)
        logo_icon.startAnimation(logoanim)
        slogan_splash.startAnimation(textanim)


        Handler().postDelayed({ //This method will be executed once the timer is over
            // Start your app main activity
            val i = Intent(this@SplashScreen, NewsActivity::class.java)
            startActivity(i)
            // close this activity
            finish()
        }, 3000)
    }
}