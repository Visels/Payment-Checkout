package com.lemu.pay.checkout.login

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lemu.pay.checkout.components.ScreenLoader
import com.lemu.pay.checkout.login.LoginViewModel.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    login:(String, String) -> Unit,
    navigateToNextScreen: () -> Unit
){

    val loginUIScreenState by viewModel.loginUiScreenState.collectAsState()
    val context = LocalContext.current


    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(it)
                .padding(16.dp)
        ) {

            Box {

                LoginContent(login)

                when(loginUIScreenState){
                    is LoginUIScreenState.INITIAL -> {}
                    is LoginUIScreenState.LOADING -> ScreenLoader()
                    is LoginUIScreenState.SUCCESS -> {
                        val username = (loginUIScreenState as LoginUIScreenState.SUCCESS).response.username
                        navigateToNextScreen.invoke()
                        Toast.makeText(context,"Logged in successfully $username",Toast.LENGTH_SHORT).show()}

                    is LoginUIScreenState.Error -> {
                        val message = (loginUIScreenState as LoginUIScreenState.Error).message
                        Toast.makeText(context,message,Toast.LENGTH_SHORT).show() }
                }
            }



        }
    }

}