package com.codeplace.literalearnsapp.presentation.bookslist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BooksListScreen(
    state: BooksListState
){

    Column(
        Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {

        LazyColumn {
            items(count = state.selfHelpGeneralVolumes!!.size){
                Text(text = state.selfHelpGeneralVolumes[it].title)
            }

        }
        if (state.isLoading!!){
            CircularProgressIndicator()
        }
        Text(
            textAlign = TextAlign.Center,
            text = "Explore the BooksInfoDto catalog",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 34.sp,
            maxLines = 2
        )

    }

}