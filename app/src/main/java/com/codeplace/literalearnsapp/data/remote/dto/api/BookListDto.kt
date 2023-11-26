package com.codeplace.literalearnsapp.data.remote.dto.api

import com.google.gson.annotations.SerializedName

data class BookListDto(
    @SerializedName("totalItems")
    val totalItems: Int?,
    @SerializedName("items")
    val items: List<ItemDto>?
)

