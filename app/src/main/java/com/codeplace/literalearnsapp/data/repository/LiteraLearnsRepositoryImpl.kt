package com.codeplace.literalearnsapp.data.repository

import android.util.Log
import com.codeplace.literalearnsapp.data.mapper.toBooksList
import com.codeplace.literalearnsapp.data.remote.api.LiteraLearnsApi
import com.codeplace.literalearnsapp.domain.repository.LiteraLearnsRepository
import com.codeplace.literalearnsapp.domain.repository.models.BooksList
import com.codeplace.literalearnsapp.util.Resource
import retrofit2.HttpException
import java.io.IOException

const val TAG = "LiteralLearnsRepositoryImpl"
class LiteraLearnsRepositoryImpl(
val api: LiteraLearnsApi
):LiteraLearnsRepository {
    override suspend fun getBooksListSelfHelpGeneral(): Resource<BooksList> {
        return try {
            val result = api.getBooksListSelfHelpGeneral()
            Log.e(TAG, "result: $result")
            Resource.Success(result.toBooksList())
        } catch (e:IOException){
            Log.e(TAG, "IOException occurred: $e")
            Resource.Error(
                message = "Couldn't load volume info"
            )
        } catch (e: HttpException){
            Log.e(TAG, "HttpException occurred: $e")
            Resource.Error(
                message = "Couldn't load volume info"
            )
        }
    }
}