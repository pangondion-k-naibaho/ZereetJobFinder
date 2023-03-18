package com.pangondionkn.zereetjobfinder.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.pangondionkn.zereetjobfinder.R
import com.pangondionkn.zereetjobfinder.databinding.ActivityPortalBinding
import com.pangondionkn.zereetjobfinder.view.fragment.AccountFragment
import com.pangondionkn.zereetjobfinder.view.fragment.HomeFragment

class PortalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPortalBinding

    companion object{
        const val RETRIEVED_USERNAME = "RETRIEVED_USERNAME"
        const val RETRIEVED_URL = "RETRIEVED_URL"
        fun newIntent(context: Context, retrievedUsername: String, retrievedUrlPhoto:String): Intent = Intent(context, PortalActivity::class.java)
            .putExtra(RETRIEVED_USERNAME, retrievedUsername)
            .putExtra(RETRIEVED_URL, retrievedUrlPhoto)
    }

    private val navListener = object : BottomNavigationView.OnNavigationItemSelectedListener{
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            var selectedItem: Fragment?= null
            when(item.itemId){
                R.id.navigation_home ->{
                    selectedItem = HomeFragment.newInstance()
                }
                R.id.navigation_account ->{
                    selectedItem = AccountFragment.newInstance("dion","aadsadds")
                }
                else ->{}
            }
            supportFragmentManager.beginTransaction().replace(R.id.container, selectedItem!!).commit()
            return true
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPortalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavBottomnFragment()
    }

    private fun setUpNavBottomnFragment(){
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_portal)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_home, R.id.navigation_account
        ).build()
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setOnNavigationItemSelectedListener(navListener)
    }
}