package com.codeplace.literalearnsapp.ui.login.view.activity.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codeplace.literalearnsapp.state.SignInState

@Composable
fun GoogleSignInScreen(state:SignInState,
                       onSignInClick:() -> Unit
){
    val context = LocalContext.current

    LaunchedEffect(key1 = state.signInError){
        state.signInError?.let {
            error ->
            Toast.makeText(
                context,
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
            , onClick = onSignInClick) {
            Text(text = "Sign in", textAlign = TextAlign.Center)
        }
    }

}