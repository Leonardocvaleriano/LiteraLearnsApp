package com.codeplace.literalearnsapp.ui.main.view.models

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle

data class NavBarItem(
    val route:String,
    val title:String,
    val textStyle: TextStyle,
    val selectedIcon: Painter,
    val unselectedIcon:Painter
)