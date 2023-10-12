package com.codeplace.literalearnsapp.ui.login.viewModel


import androidx.lifecycle.ViewModel
import com.codeplace.literalearnsapp.state.SignInState
import com.codeplace.literalearnsapp.ui.login.view.model.SignInResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class GoogleSignInViewModel: ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult){
        _state.update { it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        )}

    }

    fun resetState() {
        _state.update { it.copy(
            isSignInSuccessful = false
        ) }
    }
}