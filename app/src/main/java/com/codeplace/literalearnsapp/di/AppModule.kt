package com.codeplace.literalearnsapp.di

import android.app.Application
import com.codeplace.literalearnsapp.data.remote.api.LiteraLearnsApi
import com.codeplace.literalearnsapp.data.remote.api.LiteraLearnsApi.Companion.BASE_URL
import com.codeplace.literalearnsapp.data.remote.client.GoogleAuthUiClient
import com.codeplace.literalearnsapp.data.repository.LiteraLearnsRepositoryImpl
import com.codeplace.literalearnsapp.domain.repository.LiteraLearnsRepository
import com.google.android.gms.auth.api.identity.SignInClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesApi():LiteraLearnsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LiteraLearnsApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRepository(api:LiteraLearnsApi):LiteraLearnsRepository{
        return LiteraLearnsRepositoryImpl(api = api)
    }

//    @Provides
//    @Singleton
//    fun providesGoogleAuth(
//        app: Application
//    ): GoogleAuthUiClient {
//        return GoogleAuthUiClient(context = app)
//    }
}