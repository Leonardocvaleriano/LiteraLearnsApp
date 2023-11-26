package com.codeplace.literalearnsapp.data.remote.dto.api

import com.google.gson.annotations.SerializedName

data class ItemDto(
    @SerializedName("volumeInfo")
    val volumeInfo:VolumeInfoDto
)
