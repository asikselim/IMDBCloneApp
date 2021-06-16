package com.selimasik.imdbapp.ui.splash

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.annotation.RequiresApi
import com.selimasik.imdbapp.utils.AlertDialogUtil
import com.selimasik.imdbapp.utils.NetworkUtil
import com.selimasik.imdbapp.R
import com.selimasik.imdbapp.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        init()
    }
    private fun init() {
        val timer = object: CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            @RequiresApi(Build.VERSION_CODES.M)
            override fun onFinish() {
                if(NetworkUtil.isOnline(applicationContext)){
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    finish()
                }
                else{
                    AlertDialogUtil.alertDialogShow(this@SplashActivity, resources.getString(R.string.uyari),
                            resources.getString(R.string.internet_yok_mesaj),
                            resources.getString(R.string.ayaralara_git),
                            resources.getString(R.string.kapat),
                            resources.getString(R.string.splash_activity)

                    )


                }
            }
        }
        timer.start()
    }




}