package com.lemu.pay.checkout.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.jakewharton.threetenabp.AndroidThreeTen
import com.lemu.pay.checkout.MainActivity
import com.lemu.pay.checkout.data.local.PreferencesHelper
import com.lemu.pay.checkout.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import org.threeten.bp.LocalDate
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    private val viewModel:LoginViewModel by viewModels()

    @Inject
    lateinit var preferencesHelper: PreferencesHelper


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(2000)
        installSplashScreen()
        AndroidThreeTen.init(this)
        checkIfRequiresLogin()

        setContent{
            LoginScreen(
                login = { username,password -> login(username,password)},
                navigateToNextScreen = {navigateToMainActivity()}
            )
        }
    }

    private fun navigateToMainActivity(){
        val intent = Intent(this@LoginActivity,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun login(username:String, password:String){
        viewModel.login(username,password)
    }

    fun checkIfRequiresLogin(){
        val date:String =preferencesHelper.lastLoginDate.toString()

        if(date.equals(Constants.NO_DATE)){ return }
        else {
            val today: LocalDate = LocalDate.now()
            val lastLogin =  LocalDate.parse(date)

            if(lastLogin.equals(today)){
                navigateToMainActivity()
            }
            else {return }
        }
    }

}