package com.codeplace.literalearnsapp.navigation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.codeplace.literalearnsapp.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object SearchBooks : Screen("search_books", R.string.search_books)
    object MyBooks : Screen("my_books", R.string.my_books)
    object Learns:Screen("learns", R.string.learns)
}