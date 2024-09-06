package com.lemu.pay.checkout.payment

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.lemu.pay.checkout.mpesa.MpesaPaymentActivity
import com.lemu.pay.checkout.ui.theme.CalculatorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentActivity : ComponentActivity() {

    private val viewModel:PaymentViewModel by viewModels()

    private  var amount:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        amount = intent.getStringExtra("amount")
        Log.d("Payment", "Payment received with amount: $amount")
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent{

            val paymentViewModel:PaymentViewModel = hiltViewModel<PaymentViewModel>()

            PaymentScreen(
               amount = amount.toString().ifBlank { "0" },
                navigateBack = {finish()},
                navigateToMpesaPayment = {navigateToMpesa()}
            )
            }

    }


    private fun navigateToMpesa(){
        viewModel.fetchTodos()
        val intent = Intent(this,MpesaPaymentActivity::class.java)
        intent.putExtra("amount",amount)
        startActivity(intent)
    }


}