package com.pangondionkn.zereetjobfinder.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.pangondionkn.zereetjobfinder.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private val TAG = DashboardActivity::class.java.simpleName

    private lateinit var deliveredUsername: String
    private lateinit var deliveredUrlPhoto: String

    private lateinit var userDelivered: GoogleSignInAccount

    companion object{
        const val DELIVERED_USER = "DELIVERED_USER"
        const val USERNAME = "USERNAME"
        const val URL_PHOTO = "URL_PHOTO"
        fun newIntent(context: Context, username: String, url_photo: String): Intent = Intent(context, DashboardActivity::class.java)
            .putExtra(USERNAME, username)
            .putExtra(URL_PHOTO, url_photo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        deliveredUsername = intent.getStringExtra(USERNAME) as String
        deliveredUrlPhoto = intent.getStringExtra(URL_PHOTO) as String

        Log.d(TAG, "userDelivered: $deliveredUsername")
    }
}