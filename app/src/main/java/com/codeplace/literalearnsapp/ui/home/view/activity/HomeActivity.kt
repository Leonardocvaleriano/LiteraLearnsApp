package com.codeplace.literalearnsapp.ui.home.view.activity

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codeplace.literalearnsapp.databinding.ActivityHomeBinding
import com.codeplace.literalearnsapp.ui.home.viewModel.AuthenticationViewModel
import com.google.android.gms.tasks.OnCompleteListener
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeBinding
    private val viewModel by viewModel<AuthenticationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val serverAuthCode = intent.getStringExtra("EXTRA_SERVER_AUTH_CODE")
        val personName = intent.getStringExtra("EXTRA_PERSON_NAME")
        val personEmail = intent.getStringExtra("EXTRA_PERSON_EMAIL")



        with(binding){

            txtServerAuthCode.text = serverAuthCode.toString()
            txtPersonName.text=personName.toString()
            txtPersonEmail.text=personEmail.toString()
        }

    }


//


    private fun errorMessage(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }
    private fun loading(loading: Boolean) {
        with(binding){
            progressBar.visibility = if (loading) VISIBLE else GONE
        }
    }

}