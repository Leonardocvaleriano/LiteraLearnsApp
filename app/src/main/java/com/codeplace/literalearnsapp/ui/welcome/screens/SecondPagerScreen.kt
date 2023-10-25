package com.codeplace.literalearnsapp.ui.welcome.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SecondPagerScreen(){
    Column{
        Text(
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            text = "Organize your learnings",
            lineHeight = 36.sp
            )
        Text(
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            text = "Gather all your books and all their respective knowledge in just one place."
        )
    }
//    Column(
//        Modifier
//            .fillMaxWidth(0.6f)
//            .padding(PaddingValues(top = 16.dp, bottom = 16.dp))
//        ,horizontalAlignment = Alignment.CenterHorizontally) {
//        Text(
//            fontWeight = FontWeight.Normal,
//            fontSize = 18.sp,
//            fontStyle = FontStyle.Normal,
//            textAlign = TextAlign.Center,
//            text = "Gather all your books and all their respective knowledge in just one place."
//        )
//    }
}