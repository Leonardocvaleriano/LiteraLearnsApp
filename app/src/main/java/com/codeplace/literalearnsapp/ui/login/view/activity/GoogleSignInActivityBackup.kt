package com.codeplace.literalearnsapp.ui.login.view.activity//package com.codeplace.literalearnsapp.ui.login.view.activity
//
//import android.content.Intent
//import android.net.Uri
//import android.os.Bundle
//import android.util.Log
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.codeplace.literalearnsapp.R
//import com.codeplace.literalearnsapp.databinding.ActivityGooglesigninBinding
//import com.codeplace.literalearnsapp.ui.home.view.activity.HomeActivity
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount
//import com.google.android.gms.auth.api.signin.GoogleSignInClient
//
//
//class GoogleSignInActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityGooglesigninBinding
//
//
//   // private val RC_SIGN_IN = 9001
//
//    val TAG = "ServerAuthCodeActivity"
//    private val RC_GET_AUTH_CODE = 9003
//
//    private val mGoogleSignInClient: GoogleSignInClient? = null
//    private val mAuthCodeTextView: TextView? = null
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityGooglesigninBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//
//
//
////    fun googleSignInSetup(){
////       val serverClientId = getString(R.string.server_client_id)
////        // Configure sign-in to request the user's ID, email address, and basic
////        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
////        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
////            //.requestIdToken(getString(R.string.server_client_id))
////            .requestScopes(Scope("https://www.googleapis.com/auth/books"))
////            .requestServerAuthCode(serverClientId)
////            .requestEmail()
////            .build()
////
////        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
////        mGoogleSignInClient.signInIntent
////
////
////        // When your app starts, check if the user has already signed in to your app using Google,
////        // on this device or another device, by calling silentSignIn:
////        mGoogleSignInClient.silentSignIn()
////         .addOnCompleteListener(this) { task -> handleSignInResult(task) }
////
////        // This task is always completed immediately, there is no need to attach an
////        // asynchronous listener.
////        val task = GoogleSignIn.getSignedInAccountFromIntent(mGoogleSignInClient.signInIntent)
////        handleSignInResult(task)
////
////
////        binding.signInButton.setOnClickListener {
////            val signInIntent = mGoogleSignInClient.signInIntent
////            startActivityForResult(signInIntent, RC_SIGN_IN)
////        }
////    }
//
////    fun googleSignInSetupWithoutExistingUser(){
////        val serverClientId = getString(R.string.server_client_id)
////        // Configure sign-in to request the user's ID, email address, and basic
////        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
////        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
////            //.requestIdToken(getString(R.string.server_client_id))
////            .requestScopes(Scope("https://www.googleapis.com/auth/books"))
////            .requestServerAuthCode(serverClientId)
////            .requestEmail()
////            .build()
////
////        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
////        mGoogleSignInClient.signInIntent
////
//
//        // When your app starts, check if the user has already signed in to your app using Google,
//        // on this device or another device, by calling silentSignIn:
////        mGoogleSignInClient.silentSignIn()
//           // .addOnCompleteListener(this) { task -> handleSignInResult(task) }
//
//        // This task is always completed immediately, there is no need to attach an
//        // asynchronous listener.
////        val task = GoogleSignIn.getSignedInAccountFromIntent(mGoogleSignInClient.signInIntent)
////        handleSignInResult(task)
//
//
////        binding.signInButton.setOnClickListener {
////            val signInIntent = mGoogleSignInClient.signInIntent
////            startActivityForResult(signInIntent, RC_SIGN_IN)
////        }
////     }
//
//    // Button listeners
//
////    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
////        try {
////            val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)
////             updateUI(account)
////            // Signed in successfully, show authenticated UI.
////        } catch (e: ApiException) {
////            // The ApiException status code indicates the detailed failure reason.
////            // Please refer to the GoogleSignInStatusCodes class reference for more information.
////            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
////            //updateUI(null)
////            googleSignInSetupWithoutExistingUser()
////        }
////
////    }
//
//
//
////    override fun onStart() {
////            super.onStart()
////            //Check for an existing signed-in user
////            val account = GoogleSignIn.getLastSignedInAccount(this)
////            updateUI(account)
////
////        }
//
////    private fun updateUI(account: GoogleSignInAccount?) {
////            val authCode = account?.serverAuthCode
////            val idToken = account?.idToken
////            val personName = account?.displayName
////            val personGivenName = account?.givenName
////            val personFamilyName = account?.familyName
////            val personEmail = account?.email
////            val personId = account?.id
////            val personPhoto: Uri? = account?.photoUrl
////
////        if (authCode != null ){
////
////            val intent = Intent(this, HomeActivity::class.java).also {
////                it.putExtra("EXTRA_SERVER_AUTH_CODE", authCode)
////                it.putExtra("EXTRA_PERSON_NAME", personName)
////                it.putExtra("EXTRA_PERSON_EMAIL", personEmail)
////            }
////            startActivity(intent)
////        } else {
////            googleSignInSetupWithoutExistingUser()
////        }
////    }
//}
//
//
