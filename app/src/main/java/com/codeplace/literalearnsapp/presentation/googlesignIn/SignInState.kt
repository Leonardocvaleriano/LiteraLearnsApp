package com.codeplace.literalearnsapp.presentation.googlesignIn

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import com.codeplace.literalearnsapp.data.remote.dto.auth.UserData

data class SignInState(
    val isSignInSuccessful:Boolean = false,
    val signInError:String? = null,
    val data:UserData? = null,
    val launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>? = null
)