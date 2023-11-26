package com.codeplace.literalearnsapp.presentation.navigation.util

import androidx.annotation.StringRes
import com.codeplace.literalearnsapp.R

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    val title: String? = "",
    val contentDescription:String? = "",
    val selectedIcon: Int? = null,
    val unselectedIcon: Int? = null,
) {
    data object Welcome: Screen(
        route = "welcome",
        resourceId = R.string.welcome
    )
    data object Books : Screen(
        route = "books",
        resourceId = R.string.books,
        title = "Books",
        contentDescription = "Books screen" ,
        selectedIcon = R.drawable.menu_book_filled,
        unselectedIcon = R.drawable.menu_book_outlined,
    )

    data object MyBooks : Screen(
        route = "my_books",
        R.string.my_books,
        title = "My Books",
        contentDescription = "My books screen",
        selectedIcon = R.drawable.my_books_filled,
        unselectedIcon = R.drawable.my_books_outlined
    )

    data object Learns : Screen(
        route = "learns",
        R.string.learns,
        title = "Learns",
        contentDescription = "Learns screen",
        selectedIcon = R.drawable.learns_filled,
        unselectedIcon = R.drawable.learns_outlined
    )
}