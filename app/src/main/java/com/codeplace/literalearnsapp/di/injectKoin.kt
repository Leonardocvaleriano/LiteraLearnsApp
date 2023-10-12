package com.codeplace.literalearnsapp.di

import androidx.compose.runtime.Composable
import com.codeplace.literalearnsapp.LiteraLearnsApplication.Companion.BASE_URL_GOOGLE_API
import com.codeplace.literalearnsapp.LiteraLearnsApplication.Companion.BASE_URL_TOKEN
import com.codeplace.literalearnsapp.repository.LiteraLearnsRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {


  //  single { LiteraLearnsRepository(BASE_URL_TOKEN, BASE_URL_GOOGLE_API) }
   // viewModel { LiteraLearnsViewModel(get()) }
}