package com.codeplace.literalearnsapp.di

import com.codeplace.literalearnsapp.LiteraLearnsApplication.Companion.BASE_URL
import com.codeplace.literalearnsapp.repository.LiteraLearnsRepository
import com.codeplace.literalearnsapp.ui.home.viewModel.AuthenticationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module{

    single { LiteraLearnsRepository(BASE_URL) }
    viewModel { AuthenticationViewModel(get()) }
}