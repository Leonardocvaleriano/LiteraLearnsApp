package com.codeplace.literalearnsapp.navigation

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.codeplace.literalearnsapp.R

data class NavBarItem(
    val route:String,
    val title:String,
    val textStyle: TextStyle,
    val selectedIcon: Painter,
    val unselectedIcon:Painter
)

