package com.codeplace.literalearnsapp.data.remote

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.util.Log
import com.codeplace.literalearnsapp.R
import com.codeplace.literalearnsapp.data.models.SignInResult
import com.codeplace.literalearnsapp.data.models.UserData
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException


/*
This class will be used to:
    - sign in google and use it sign in to make the firebase Auth.
    - sign out
    - get the user info
 */

class GoogleAuthUiClient(
    private val context:Context,
    private  val oneTapClient: SignInClient
){

    private val auth = Firebase.auth
    val TAG = "GoogleAuthUiClient"


    private fun buildSignInRequest():BeginSignInRequest{
        return BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(context.getString(R.string.server_web_client_id))
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(true)
                    .build())
            // Automatically sign in when exactly one credential is retrieved.
            .setAutoSelectEnabled(true)
            .build()
    }
    suspend fun signIn():IntentSender? {
        val result = try {
            // We are using await() function here,
            // which will suspend out this courotine and wait until the buildsigninRequest finish it execution.
            oneTapClient.beginSignIn(
                buildSignInRequest()).await()
        } catch (e:Exception)   {
            e.printStackTrace()
            Log.d(TAG,"${e.message}")
            if(e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    fun getSignedInUser(): UserData? = auth.currentUser?.run {
        UserData(
            userId = uid,
            userName = displayName,
            userEmail = email,
            profilePictureUrl = photoUrl.toString()
        )
    }

    suspend fun signOut(){
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e:Exception){
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }


    suspend fun signInWithIntent(intent:Intent): SignInResult {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user = auth.signInWithCredential(googleCredentials).await().user
            SignInResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        userName = displayName,
                        userEmail = email,
                        profilePictureUrl = photoUrl.toString()
                    )
                },
                errorMessage = null
            )
        } catch (e:Exception)   {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(data = null,
                errorMessage = e.message)
        }
    }

}