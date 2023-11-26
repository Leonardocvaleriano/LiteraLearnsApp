package com.codeplace.literalearnsapp.presentation.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codeplace.literalearnsapp.presentation.navigation.util.Screen
import com.codeplace.literalearnsapp.presentation.bookslist.BooksListScreen
import com.codeplace.literalearnsapp.presentation.bookslist.BooksListViewModel
import com.codeplace.literalearnsapp.presentation.learns.LearnsScreen
import com.codeplace.literalearnsapp.presentation.mybookslist.MyBooksScreen


@Composable
fun BottomBarGraph(
    navController2: NavHostController,
    paddingValues: PaddingValues
) {

    NavHost(
        navController = navController2,
        startDestination = Screen.Books.route,
        modifier = Modifier.padding(paddingValues)
    ) {

        composable(Screen.Books.route) {
            val viewModel = hiltViewModel<BooksListViewModel>()
            val state = viewModel.bookListSelfHelpGeneralState
            BooksListScreen(state = state)

        }
        composable(Screen.MyBooks.route) {
            MyBooksScreen()

        }
        composable(Screen.Learns.route) {
            LearnsScreen()

        }
    }
}