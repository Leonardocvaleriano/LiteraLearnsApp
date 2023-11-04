package com.codeplace.literalearnsapp.navigation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.codeplace.literalearnsapp.R

sealed class Screen(val route: String, val resourceId: Int) {
    object SearchBooks : Screen("search_books", 0)
    object MyBooks : Screen("my_books",1)
    object Learns:Screen("learns",2)
}