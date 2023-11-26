package com.codeplace.literalearnsapp.presentation.bookslist

import com.codeplace.literalearnsapp.domain.repository.models.Item
import com.codeplace.literalearnsapp.domain.repository.models.VolumeInfo

data class BooksListState(
    val juvenileFictionVolumes: List<VolumeInfo>? = emptyList(),
    val selfHelpGeneralVolumes:List<VolumeInfo>? = emptyList(),
    val isLoading:Boolean? = false,
    val error:String? = null
)
