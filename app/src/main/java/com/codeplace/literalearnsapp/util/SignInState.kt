package com.codeplace.literalearnsapp.util

data class SignInState(
    val isSignInSuccessful:Boolean = false,
    val signInError:String? = null
)