package com.codeplace.literalearnsapp.presentation.bookslist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeplace.literalearnsapp.domain.repository.LiteraLearnsRepository
import com.codeplace.literalearnsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksListViewModel @Inject constructor(
    val repository: LiteraLearnsRepository
) : ViewModel() {

    var bookListSelfHelpGeneralState by mutableStateOf(BooksListState())

    init {
        loadBookListSelfHelpGeneral()
    }

    private fun loadBookListSelfHelpGeneral() {
        viewModelScope.launch {
            bookListSelfHelpGeneralState = bookListSelfHelpGeneralState.copy(isLoading = true)
            val bookInfoResult = async { repository.getBooksListSelfHelpGeneral() }
            when (val result = bookInfoResult.await()) {
                is Resource.Success -> {
                    bookListSelfHelpGeneralState = bookListSelfHelpGeneralState.copy(
                        isLoading = false,
                        error = null,
                        selfHelpGeneralVolumes = result.data?.items?.map { item ->
                            item.volumeInfo}
                    )
                }

                is Resource.Error ->
                    bookListSelfHelpGeneralState = bookListSelfHelpGeneralState.copy(
                        isLoading = false,
                        error = result.message
                    )

                is Resource.Loading -> {
                    bookListSelfHelpGeneralState.copy(
                        isLoading = true,
                        error = null
                    )
                }
            }
        }
    }
}