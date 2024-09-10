package com.lemu.pay.checkout.mpesa_status

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lemu.pay.checkout.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MpesaStatusActivity: ComponentActivity() {

    val statusViewModel:MpesaStatusViewModel by viewModels()

    private lateinit var transactionId:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transactionId = intent.getStringExtra("transactionId").toString()
        Log.d("RECEIVED TRANSACTION ID", transactionId)
        setContent {
            MpesaStatusScreen(
                navigateBack = {finish()},
                checkStatus = {checkTransactionStatus(transactionId)},
                navigateToHome = {navigateToHome()}

            )
        }
    }

    private fun checkTransactionStatus (transactionId:String){
        statusViewModel.fetchTransactionStatus(transactionId);
    }

    private fun navigateToHome(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}