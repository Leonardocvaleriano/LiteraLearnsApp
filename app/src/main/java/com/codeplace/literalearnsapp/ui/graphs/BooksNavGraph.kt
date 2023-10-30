package com.codeplace.literalearnsapp.ui.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codeplace.literalearnsapp.ui.learns.screen.LearnsScreen
import com.codeplace.literalearnsapp.ui.main.view.activity.MainActivity.Graph
import com.codeplace.literalearnsapp.ui.mybooks.screen.MyBooksScreen
import com.codeplace.literalearnsapp.ui.searchbooks.screen.SearchBooksScreen

@Composable
fun BooksNavGraph(
    navController:NavHostController,
    paddingValues: PaddingValues) {

    NavHost(
        navController = navController,
        startDestination = "search_books",
        route = Graph.BOTTOM_NAV
    ) {
        composable("search_books") {
            SearchBooksScreen(paddingValues)
        }
        composable("my_books"){
            MyBooksScreen(paddingValues)
        }
        composable("learns"){
            LearnsScreen(paddingValues)
        }
    }
}

