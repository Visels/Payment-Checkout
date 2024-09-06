package com.lemu.pay.checkout.mpesa

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MpesaPaymentActivity :ComponentActivity() {


    private val viewModel:MpesaPaymentViewModel by viewModels()

    var amount:String? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        amount = intent.getStringExtra("amount")
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent{
            MpesaPaymentScreen(
                navigateBack = {finish()},
                sendStkPush = {sendStkRequest(it)},
                amount = amount
            )
        }
    }


    private fun sendStkRequest(mobile:String){
        Log.d("SENDING STK","Mobile: $mobile Amount: $amount")
        amount?.let {
            viewModel.initiateSTK(
                mobile = mobile,
                amount = it
            )
        }
    }
}