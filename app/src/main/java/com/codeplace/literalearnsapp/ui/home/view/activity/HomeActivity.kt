package com.codeplace.literalearnsapp.ui.home.view.activity

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codeplace.literalearnsapp.R
import com.codeplace.literalearnsapp.databinding.ActivityHomeBinding
import com.codeplace.literalearnsapp.stateFlow.StateFlow
import com.codeplace.literalearnsapp.ui.home.viewModel.AuthenticationViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeBinding
    private val viewModel by viewModel<AuthenticationViewModel>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val authCode = intent.getStringExtra("EXTRA_SERVER_AUTH_CODE")
        val personName = intent.getStringExtra("EXTRA_PERSON_NAME")
        val personEmail = intent.getStringExtra("EXTRA_PERSON_EMAIL")

//        val sharedPreferences = this.getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE)
//        sharedPreferences.edit().putString("CLIENT_SECRET")



        if (authCode != null){

            initValues(authCode)
            initObservables()

            with(binding){
                txtServerAuthCode.text = authCode.toString()
                txtPersonName.text= personName.toString()
                txtPersonEmail.text=personEmail.toString()
            }
        } else {
            with(binding){
                txtServerAuthCode.text = "Server code is null"
            }
        }


    }
    private fun initValues(authCode:String?) {
        viewModel.getTokenAuthenticated(getString(R.string.server_client_id),getString(R.string.client_secret),authCode,getString(R.string.grant_type),getString(R.string.redirect_uri))
    }
    private fun initObservables() {
        viewModel.tokenAuthenticated.observe(this){
            when(it){
                is StateFlow.Loading ->{loading(it.loading)}
                is StateFlow.Success<*>->initTokenAuthenticated(it.data as JSONObject)
                is StateFlow.Error->{errorMessage(it.errorMessage)}

            }
        }
    }
    private fun initTokenAuthenticated(result:JSONObject) {
        val test = result
    }


    private fun errorMessage(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }
    private fun loading(loading: Boolean) {
        with(binding){
            progressBar.visibility = if (loading) VISIBLE else GONE
        }
    }

}