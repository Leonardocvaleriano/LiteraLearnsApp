package com.codeplace.literalearnsapp.data.remote.dto.auth


data class SignInResult(
    val data: UserData? = null,
    val errorMessage:String? = null
)
data class UserData(
    val userId:String? = null,
    val userName:String?= null,
    val userEmail:String?= null,
    val profilePictureUrl:String? = null
)
