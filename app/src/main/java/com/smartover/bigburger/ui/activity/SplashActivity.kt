package com.smartover.bigburger.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rbddevs.splashy.Splashy
import com.smartover.bigburger.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splashy()
    }

    private fun splashy() {
        Splashy(this)
            .setLogo(R.drawable.burger_large)
            .setTitle(R.string.app_name)
            .setTime(2000)
            .setAnimation(Splashy.Animation.SLIDE_IN_TOP_BOTTOM)
            .setTitleColor(R.color.black)
            .setBackgroundColor(R.color.colorAccent)
            .show()


        Splashy.onComplete(object : Splashy.OnComplete {
            override fun onComplete() {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }

        })
    }

}
