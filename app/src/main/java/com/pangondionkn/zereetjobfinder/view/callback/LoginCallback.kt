package com.pangondionkn.zereetjobfinder.view.callback

import com.google.android.gms.auth.api.signin.GoogleSignInClient

interface LoginCallback {
    fun onSignInStarted(client: GoogleSignInClient?)
}