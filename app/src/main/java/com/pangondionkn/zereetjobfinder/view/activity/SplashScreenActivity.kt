package com.pangondionkn.zereetjobfinder.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.pangondionkn.zereetjobfinder.databinding.ActivitySplashscreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashscreenBinding
    private val TAG = SplashScreenActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setUpSplashScreen()
    }

    private fun setUpSplashScreen(){
        Handler().postDelayed(
            {
                startActivity(LoginActivity.newIntent(this@SplashScreenActivity))
                finish()
            },4000
        )
    }
}