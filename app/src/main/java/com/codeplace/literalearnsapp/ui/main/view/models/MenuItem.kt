package com.codeplace.literalearnsapp.ui.main.view.models

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle

data class MenuItem(
    val id:String,
    val title:String,
    val description:String = "",
    val contentDescription:String,
    val textStyle: TextStyle,
    val selectedIcon:ImageVector,
    val unselectedIcon:ImageVector
)
