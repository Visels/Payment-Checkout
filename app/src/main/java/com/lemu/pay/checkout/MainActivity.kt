package com.lemu.pay.checkout

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lemu.pay.checkout.data.local.PreferencesHelper
import com.lemu.pay.checkout.login.LoginActivity
import com.lemu.pay.checkout.payment.PaymentActivity
import com.lemu.pay.checkout.ui.theme.CalculatorTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferencesHelper: PreferencesHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)


        setContent {
//            val systemUiController = rememberSystemUiController()
//            val darkTheme = !isSystemInDarkTheme()

//            DisposableEffect(systemUiController, darkTheme) {
//                systemUiController.setSystemBarsColor(
//                    color = Color.Transparent,
//                    darkIcons = darkTheme
//                )
//
//                onDispose {}
//            }

                CheckoutApp(
                    navigateToPayment = {navigateToPayment(it)},
                    logout = {logout()}
                )

        }
    }


    fun navigateToPayment(amount:String){
        val intent = Intent(this,PaymentActivity::class.java)
        intent.putExtra("amount",amount)
        startActivity(intent)
    }

    fun logout(){
        Log.d("LOGOUT", "Logout clicked")
        startActivity(Intent(this@MainActivity,LoginActivity::class.java))
        preferencesHelper.clear()
        finish()
    }

}