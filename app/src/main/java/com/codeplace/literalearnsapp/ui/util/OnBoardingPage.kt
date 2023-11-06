package com.codeplace.literalearnsapp.ui.util

import com.codeplace.literalearnsapp.R

sealed class OnBoardingPage (
    val image:Int,
    val title:String,
    val description:String
) {
    object FirstPage:OnBoardingPage(
        image = R.drawable.welcome,
        title = "Welcome",
        description = "This is your books collections of knowledge"
    )
    object SecondPage:OnBoardingPage(
        image = R.drawable.organize,
        title = "Organize your learns",
        description = "Gather all your books and all their respective knowledge in just one place."

    )
    object ThirdPage:OnBoardingPage(
        image = R.drawable.dont_forget,
        title = "In Construction",
        description = "In construction.."
    )

    object FourthPage:OnBoardingPage(
        image = R.drawable.login,
        title = "Log in with Google",
        description = "Log in to share your books across devices."

    )

}


