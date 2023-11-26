package com.codeplace.literalearnsapp.presentation.googlesignIn

sealed class SignInEvent{
    object SignIn: SignInEvent()
    object Logout: SignInEvent()

}