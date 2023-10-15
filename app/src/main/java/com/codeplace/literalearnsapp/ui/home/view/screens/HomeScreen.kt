package com.codeplace.literalearnsapp.ui.home.view.screens

import Drawer
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(isUserLogged: Boolean, navController: NavController) {

 Drawer(
  isUserLogged = isUserLogged,
  profilePictureUrl = "",
  userName = "LeonardoTeste",
  userEmail = "leonardova@teste.com",
  navController = navController
 )
}



