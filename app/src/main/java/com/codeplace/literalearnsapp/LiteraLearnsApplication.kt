package com.codeplace.literalearnsapp

import com.codeplace.literalearnsapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class LiteraLearnsApplication:android.app.Application() {

    var  appcontext:android.content.Context? = null

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@LiteraLearnsApplication)
            modules(appModule)
        }
    }

    companion object {
        const val BASE_URL = "https://www.googleapis.com/"
    }
}