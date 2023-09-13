package com.codeplace.literalearnsapp.ui.baseViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeplace.literalearnsapp.stateFlow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response

open class BaseViewModel:ViewModel() {

    fun fetchData(
        liveData: MutableLiveData<StateFlow>,
        service: suspend () -> Response<*>){

        viewModelScope.launch {
            liveData.value = StateFlow.Loading(true)

            try {
                val response = service()
                liveData.value = StateFlow.Loading(false)
                if (response.isSuccessful){
                    val jsonResponse = JSONObject(response.body()!! as Map<*, *>)
                    liveData.value = StateFlow.Success(jsonResponse)
                }  else if(response.code() == 504) {
                    liveData.value = StateFlow.Error(
                        "An Error occurred when tried to call the service, please try again.",
                        null,
                        null,
                        null
                    )
                } else {
                    liveData.value = StateFlow.Error(response.errorBody()!!.string(), null,null,null)
                }
            } catch (e: Exception) {
                Log.e("VmViewModel", Log.getStackTraceString(e))
                liveData.value = StateFlow.Loading(false)
                liveData.value = StateFlow.Error(e.message!!, null, null, null)
            }
        }
    }

}
