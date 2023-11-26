package com.codeplace.literalearnsapp.presentation.navigation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Share
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

data class MenuItem(
    val id:String,
    val title:String,
    val description:String? = "",
    val contentDescription:String,
    val textStyle: TextStyle,
    val selectedIcon: ImageVector,
    val unselectedIcon:ImageVector,
)


