package com.codeplace.literalearnsapp.ui.login.viewModel


import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeplace.literalearnsapp.remote.GoogleAuthUiClient
import com.codeplace.literalearnsapp.state.SignInState
import com.codeplace.literalearnsapp.ui.login.view.model.SignInResult
import com.codeplace.literalearnsapp.ui.login.view.model.UserData
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class GoogleSignInViewModel(
    private val googleAuthUiClient: GoogleAuthUiClient
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    private val _userDataState = MutableStateFlow(SignInResult())
    val userState = _userDataState.asStateFlow()


    fun getSignInIntentSender(launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>) {
        viewModelScope.launch {
            val signInIntentSender = googleAuthUiClient.signIn()
            launcher.launch(
                IntentSenderRequest.Builder(
                    signInIntentSender ?: return@launch
                ).build()
            )
        }
    }

    fun getLauncherForActivityResult(result: ActivityResult) {
        viewModelScope.launch {
            val signInResult = googleAuthUiClient.signInWithIntent(
                intent = result.data ?: return@launch
            )
            onSignInResult(signInResult)
        }
    }

    fun onSignInResult(result: SignInResult) {
        _state.update {
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
        _userDataState.update {
            it.copy(
                data = result.data
            )
        }

    }

    fun resetState() {
        _state.update {
            it.copy(
                isSignInSuccessful = false
            )
        }
    }

    fun getSignedInUser() {
        val userData = googleAuthUiClient.getSignedInUser()
        _userDataState.update {
            it.copy(
                data = userData
            )
        }
    }

    fun singOut() {
        viewModelScope.launch {
            googleAuthUiClient.signOut()
        }
    }

    fun resetUserDataState(){
        _userDataState.update {
                it.copy(
                    data = null
                )
            }

        }
    }
