package com.codeplace.literalearnsapp.state

data class SignInState(
    val isSignInSuccessful:Boolean = false,
    val signInError:String? = null
)