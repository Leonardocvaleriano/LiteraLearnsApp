package com.codeplace.literalearnsapp.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codeplace.literalearnsapp.navigation.Screen
import com.codeplace.literalearnsapp.screens.LearnsScreen
import com.codeplace.literalearnsapp.screens.MyBooksScreen
import com.codeplace.literalearnsapp.screens.SearchBooksScreen
import com.codeplace.literalearnsapp.viewmodel.AppNavigationViewModel

@Composable
fun BottomBarGraph(navController:NavHostController, paddingValues:PaddingValues, viewModelApp: AppNavigationViewModel){


    NavHost(
        navController = navController,
        route = Graph.BOTTOM_BAR,
        startDestination = Screen.SearchBooks.route,
        modifier = androidx.compose.ui.Modifier.padding(paddingValues)
    ) {
        composable(Screen.SearchBooks.route) {
            viewModelApp.updateSelectedBottomItemIndex(Screen.SearchBooks.resourceId)
            SearchBooksScreen()

        }
        composable(Screen.MyBooks.route) {
            viewModelApp.updateSelectedBottomItemIndex(Screen.MyBooks.resourceId)
            MyBooksScreen()

        }
        composable(Screen.Learns.route){
            viewModelApp.updateSelectedBottomItemIndex(Screen.Learns.resourceId)
            LearnsScreen()

        }
    }
}