package com.codeplace.literalearnsapp.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codeplace.literalearnsapp.navigation.Screen
import com.codeplace.literalearnsapp.ui.screens.LearnsScreen
import com.codeplace.literalearnsapp.ui.screens.MyBooksScreen
import com.codeplace.literalearnsapp.ui.screens.SearchBooksScreen

@Composable
fun BottomBarGraph(navController:NavHostController, paddingValues:PaddingValues){
    NavHost(
        navController = navController,
        route = Graph.BOTTOM_BAR,
        startDestination = Screen.SearchBooks.route,
        modifier = androidx.compose.ui.Modifier.padding(paddingValues)
    ) {
        composable(Screen.SearchBooks.route) {
            SearchBooksScreen()
        }
        composable(Screen.MyBooks.route) {
            MyBooksScreen()
        }
        composable(Screen.Learns.route){
            LearnsScreen()
        }
    }
}