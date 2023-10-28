package com.codeplace.literalearnsapp.ui.home.viewmodel.Screen

import TopAppBarWithDrawer
import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@Composable
fun HomeScreen(navController:NavController) {

    TopAppBarWithDrawer(navController = navController)
    //BottomAppCustom()
}