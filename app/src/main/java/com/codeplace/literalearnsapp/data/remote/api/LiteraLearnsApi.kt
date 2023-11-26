package com.codeplace.literalearnsapp.data.remote.api

import com.codeplace.literalearnsapp.data.remote.dto.api.BookListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface LiteraLearnsApi {


    //books/v1/volumes?q=business+subject:self-help+general
    @GET("books/v1/volumes?q=self-help+general")
    suspend fun getBooksListSelfHelpGeneral():BookListDto

    companion object{
        const val BASE_URL = "https://www.googleapis.com/"
    }

}