package com.codeplace.literalearnsapp.presentation.googlesignIn


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class GoogleSignInViewModel() : ViewModel() {

    var state by mutableStateOf(SignInState())
    private set

//    private val _state = MutableStateFlow(SignInState())
//    val state = _state.asStateFlow()

//    private val _userDataState = MutableStateFlow(SignInResult())
//    val userDataState = _userDataState.asStateFlow()

    fun onEvent(event: SignInEvent){
        when(event){
            SignInEvent.SignIn -> {
               // signIn(state.launcher)
            }
            SignInEvent.Logout -> {}
        }
    }
//    fun saveLauncher(launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>){
//        state = state.copy(
//            launcher = launcher
//        )
//    }


//    fun signIn(launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>?) {
//        viewModelScope.launch {
//            val signInIntentSender = googleAuthUiClient.signIn()
//            launcher!!.launch(
//                IntentSenderRequest.Builder(
//                    signInIntentSender ?: return@launch
//                ).build()
//            )
//        }
//    }
//
//    fun getLauncherForActivityResult(result: ActivityResult) {
//        viewModelScope.launch {
//            val signInResult = googleAuthUiClient.signInWithIntent(
//                intent = result.data ?: return@launch
//            )
//            onSignInResult(signInResult)
//        }
//    }
//
//    fun onSignInResult(result: SignInResult) {
//        state = state.copy(
//            isSignInSuccessful = result.data != null,
//            signInError = result.errorMessage,
//            data = result.data
//        )
////        _state.update {
////            it.copy(
////                isSignInSuccessful = result.data != null,
////                signInError = result.errorMessage,
////                data = result.data
////            )
////        }
////        _userDataState.update {
////            it.copy(
////                data = result.data
////            )
////        }
//
//    }
//
//    fun resetState() {
//        state = state.copy(
//            isSignInSuccessful = false
//        )
//
////        _state.update {
////            it.copy(
////                isSignInSuccessful = false
////            )
////        }
//    }
//
//    fun getSignedInUser() {
//        val userData = googleAuthUiClient.getSignedInUser()
//        state = state.copy(
//            isSignInSuccessful = userData!=null,
//            signInError = null,
//            data = userData
//        )
//
////        _state.update {
////            it.copy(
////                isSignInSuccessful = userData != null,
////                signInError = null,
////                data = userData
////            )
////        }
//    }
//
//    fun singOut() {
//        viewModelScope.launch {
//            googleAuthUiClient.signOut()
//        }
//    }
//
////    fun resetUserDataState(){
////        _userDataState.update {
////                it.copy(
////                    data = null
////                )
////            }
////
////        }
    }
