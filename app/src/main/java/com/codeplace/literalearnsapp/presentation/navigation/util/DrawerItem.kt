package com.codeplace.literalearnsapp.presentation.navigation.util

import androidx.annotation.StringRes
import com.codeplace.literalearnsapp.R

sealed class DrawerItem(
    @StringRes val resourceId:Int,
    val title:String,
    val contentDescription:String,
    val description:String? = "",
    val iconPainter:Int? = null

){
    data object Share: DrawerItem(
        resourceId = R.string.share,
        title = "Share",
        contentDescription = "Share",
        iconPainter = R.drawable.outline_share_24
    )

    data object About: DrawerItem(
        resourceId = R.string.about,
        title = "About",
        contentDescription = "About",
        iconPainter = R.drawable.outline_info_24,
    )
    data object Login: DrawerItem(
        resourceId = R.string.login,
        title = "Login",
        description = "Login in to share your books across devices",
        contentDescription = "Login",
        iconPainter = R.drawable.outline_login_24,
    )
    data object Logout: DrawerItem(
        resourceId = R.string.logout,
        title = "Logout",
        contentDescription = "Logout",
        iconPainter = R.drawable.outline_logout_24
    )

}

// Items inside the drawer as list.
//    val drawerItems = listOf(
//        MenuItem(
//            id = "share",
//            title = "Share",
//            contentDescription = "Share",
//            textStyle = TextStyle(
//                fontSize = 14.sp
//            ),
//            selectedIcon = Icons.Filled.Share,
//            unselectedIcon = Icons.Outlined.Share
//
//        ),
//        MenuItem(
//            id = "about",
//            title = "About",
//            contentDescription = "About",
//            textStyle = TextStyle(fontSize = 14.sp),
//            selectedIcon = Icons.Default.Info,
//            unselectedIcon = Icons.Outlined.Info
//        ),
//        MenuItem(
//            id = loginId,
//            title = loginTitle,
//            description = "Login in to share your books across devices",
//            contentDescription = "sign in with google",
//            textStyle = TextStyle(fontSize = 14.sp),
//            selectedIcon = Icons.Default.ExitToApp,
//            unselectedIcon = Icons.Outlined.ExitToApp
//        )
//    )

