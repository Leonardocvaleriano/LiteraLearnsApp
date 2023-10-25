package com.codeplace.literalearnsapp.ui.login.view.model


data class SignInResult(
    val data:UserData?,
    val errorMessage:String?
)
data class UserData(
    val userId:String? = null,
    val userName:String?= null,
    val userEmail:String?= null,
    val profilePictureUrl:String? = null
)
