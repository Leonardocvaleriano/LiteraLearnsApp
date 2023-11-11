package com.codeplace.literalearnsapp.screens.welcome

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.codeplace.literalearnsapp.navigation.graphs.Graph
import com.codeplace.literalearnsapp.viewmodel.GoogleSignInViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun GoogleSignInScreen(navController:NavController){

    val applicationContext = LocalContext.current
    val viewModel: GoogleSignInViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == ComponentActivity.RESULT_OK) {
                viewModel.getLauncherForActivityResult(result)
            }
        }
    )

    LaunchedEffect(key1 = state.isSignInSuccessful) {
        if (state.isSignInSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Sign in successful",
                        Toast.LENGTH_LONG
                    ).show()

            navController.popBackStack()
            navController.navigate(Graph.BOTTOM_BAR)
            viewModel.resetState()
        }
    }


    LaunchedEffect(key1 = state.signInError){
        state.signInError?.let {
            error ->
            Toast.makeText(
                applicationContext,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    Column{
        Text(
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            text = "Log in with Google",
            lineHeight = 36.sp
        )
        Text(
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            text = "Log in to share your books across devices."
        )
        Button( modifier=Modifier
            .padding(PaddingValues(top = 50.dp))
            , onClick = {
                viewModel.getSignInIntentSender(launcher = launcher)
            }) {
            Text(text = "Sign in", textAlign = TextAlign.Center)
        }
    }

}