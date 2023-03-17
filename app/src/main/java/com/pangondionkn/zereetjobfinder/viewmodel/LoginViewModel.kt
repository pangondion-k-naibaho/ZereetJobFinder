package com.pangondionkn.zereetjobfinder.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pangondionkn.zereetjobfinder.R
import com.pangondionkn.zereetjobfinder.model.utils.INFO_NEEDED.Companion.web_client_id
import com.pangondionkn.zereetjobfinder.view.callback.LoginCallback

class LoginViewModel(private val app: Application, private val loginCallback: LoginCallback): AndroidViewModel(app) {
    private var auth: FirebaseAuth = Firebase.auth
    private val _currentUser = MutableLiveData<FirebaseUser>()
    val currentUser: LiveData<FirebaseUser> = _currentUser

    private val TAG = LoginViewModel::class.java.simpleName

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(web_client_id)
        .requestEmail()
        .build()

    private val googleSignInClient = GoogleSignIn.getClient(app, gso)

    fun firebaseAuthWithGoogle(idToken: String){
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            Log.d(TAG, "current User: ${auth.currentUser}")
            if (it.isSuccessful){
                _currentUser.value = auth.currentUser
            }else{
                _currentUser.value = null
            }
        }
    }

    fun signIn(){
        loginCallback.onSignInStarted(googleSignInClient)
    }

    fun signOut(){
        auth.signOut()
    }
}