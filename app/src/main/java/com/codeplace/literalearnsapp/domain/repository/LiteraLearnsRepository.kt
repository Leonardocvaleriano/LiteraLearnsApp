package com.codeplace.literalearnsapp.domain.repository

import com.codeplace.literalearnsapp.domain.repository.models.BooksList
import com.codeplace.literalearnsapp.util.Resource

interface LiteraLearnsRepository {
    suspend fun getBooksListSelfHelpGeneral():Resource<BooksList>
}