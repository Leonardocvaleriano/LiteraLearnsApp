package com.codeplace.literalearnsapp.ui.login.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
 import com.codeplace.literalearnsapp.databinding.ActivityGooglesigninBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class GoogleSignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGooglesigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGooglesigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            //.requestIdToken()
            .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

    }

    override fun onStart() {
        super.onStart()
        //Check for an existing signed-in user
        val account = GoogleSignIn.getLastSignedInAccount(this)
        updateUI(account)

    }

    private fun updateUI(account: GoogleSignInAccount?) {

        // I did this if by myself.
        if (account != null){
            // Launch the main activity or whatever is appropriate for the app.
        }
    }
}