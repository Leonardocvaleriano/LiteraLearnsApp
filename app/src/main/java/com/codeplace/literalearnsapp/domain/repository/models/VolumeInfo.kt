package com.codeplace.literalearnsapp.domain.repository.models

import com.codeplace.literalearnsapp.data.remote.dto.api.ImageLinksDto

data class VolumeInfo(
    val imageLinks: ImageLinks,
    val description: String,
    val maturityRating: String,
    val pageCount: Int,
    val publishedDate: String,
    val publisher: String,
    val subtitle: String,
    val title: String
)

