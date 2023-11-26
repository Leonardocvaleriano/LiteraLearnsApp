package com.codeplace.literalearnsapp.data.remote.dto.api

import com.codeplace.literalearnsapp.domain.repository.models.ImageLinks
import com.google.gson.annotations.SerializedName

data class VolumeInfoDto(
    @SerializedName("imageLinks")
    val imageLinks: ImageLinksDto?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("maturityRating")
    val maturityRating: String?,
    @SerializedName("pageCount")
    val pageCount: Int?,
    @SerializedName("publishedDate")
    val publishedDate: String?,
    @SerializedName("publisher")
    val publisher: String?,
    @SerializedName("subtitle")
    val subtitle: String?,
    @SerializedName("title")
    val title: String?
)



