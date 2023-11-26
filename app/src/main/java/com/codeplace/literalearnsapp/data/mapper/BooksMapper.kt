package com.codeplace.literalearnsapp.data.mapper

import com.codeplace.literalearnsapp.data.remote.dto.api.BookListDto
import com.codeplace.literalearnsapp.domain.repository.models.BooksList
import com.codeplace.literalearnsapp.domain.repository.models.ImageLinks
import com.codeplace.literalearnsapp.domain.repository.models.Item
import com.codeplace.literalearnsapp.domain.repository.models.VolumeInfo

fun BookListDto.toBooksList(): BooksList {
    return BooksList(
        totalItems = totalItems ?: 0,
        items = items!!.map {
            Item(
                volumeInfo = (
                        VolumeInfo(
                            subtitle = it.volumeInfo.subtitle ?: "",
                            title = it.volumeInfo.title ?: "",
                            pageCount = it.volumeInfo.pageCount ?: 0,
                            publisher = it.volumeInfo.publisher ?: "",
                            publishedDate = it.volumeInfo.publishedDate ?: "",
                            maturityRating = it.volumeInfo.maturityRating ?: "",
                            imageLinks = ImageLinks(
                                smallThumbnail = it.volumeInfo.imageLinks!!.smallThumbnail ?: "",
                                thumbnail = it.volumeInfo.imageLinks.thumbnail ?: ""
                            ),
                            description = it.volumeInfo.description ?: "",

                            )
                        )
            )
        }


    )
}