package com.pangondionkn.zereetjobfinder.view.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseUser
import com.pangondionkn.zereetjobfinder.databinding.ActivityLoginBinding
import com.pangondionkn.zereetjobfinder.view.callback.LoginCallback
import com.pangondionkn.zereetjobfinder.viewmodel.LoginViewModel
import com.pangondionkn.zereetjobfinder.viewmodel.LoginViewModelFactory
import kotlin.math.log

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val TAG = LoginActivity::class.java.simpleName
    private lateinit var loginViewModel: LoginViewModel

    private lateinit var deliveredUser: FirebaseUser

    companion object{
        fun newIntent(context: Context) = Intent(context, LoginActivity::class.java)
        private const val RC_SIGN_IN = 9001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val application = requireNotNull(this).application

        val factory = LoginViewModelFactory(application, object: LoginCallback{
            override fun onSignInStarted(client: GoogleSignInClient?) {
                startActivityForResult(client?.signInIntent!!, RC_SIGN_IN)
            }
        })

        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        setUpSignInGoogle()

        loginViewModel.currentUser.observe(this, {userRetrieved ->
            setUser(userRetrieved)
        })
    }

    private fun setUser(userRetrieved:FirebaseUser){
        deliveredUser = userRetrieved
        Log.d(TAG, "deliveredUser : $deliveredUser")
    }

    private fun setUpSignInGoogle(){
        binding.btnAuthgoogle.setOnClickListener {
            loginViewModel.signIn()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "req Code: $requestCode, res Code: $resultCode, data: $data")
        if(requestCode == RC_SIGN_IN && data!=null){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                loginViewModel.firebaseAuthWithGoogle(account.idToken!!)
                Log.d(TAG, "User ${account.displayName} Signed in Successfully")
            }catch (e:ApiException){
                Log.e(TAG, e.message.toString())
            }
        }
    }
}