package com.codeplace.literalearnsapp.di

import com.codeplace.literalearnsapp.remote.GoogleAuthUiClient
import com.codeplace.literalearnsapp.ui.login.viewModel.GoogleSignInViewModel
import com.google.android.gms.auth.api.identity.Identity
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.lang.reflect.Array.get


val appModule = module {
    //  single { LiteraLearnsRepository(BASE_URL_TOKEN, BASE_URL_GOOGLE_API) }
    // viewModel { LiteraLearnsViewModel(get()) }

    single {GoogleAuthUiClient(
        context = androidContext(),
        oneTapClient = Identity.getSignInClient(androidContext())
    )}
    viewModel{GoogleSignInViewModel(get())}

}