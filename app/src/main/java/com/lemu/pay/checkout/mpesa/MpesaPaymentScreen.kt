package com.lemu.pay.checkout.mpesa

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lemu.pay.checkout.components.ScreenLoader
import com.lemu.pay.checkout.mpesa_status.MpesaStatusActivity
import com.lemu.pay.checkout.payment.PaymentAppBar
import com.lemu.pay.checkout.payment.PaymentUserDetailsInput
import com.lemu.pay.checkout.ui.theme.bluePrimary
import com.lemu.pay.checkout.ui.theme.blueSecondary
import com.lemu.pay.checkout.ui.theme.poppins_light
import com.lemu.pay.checkout.ui.theme.poppins_medium
import com.lemu.pay.checkout.ui.theme.poppins_regular
import com.lemu.pay.checkout.ui.theme.poppins_semi_bold


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MpesaPaymentScreen(
    mpesaPaymentViewModel: MpesaPaymentViewModel = viewModel(),
    amount:String?,
    navigateBack:()->Unit,
    sendStkPush:(String)->Unit,
    navigateToStatus:(String) -> Unit
){
    val screenState by mpesaPaymentViewModel.uiScreenState.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
        PaymentAppBar(
            title = "MPESA",
            onNavigationClick = navigateBack)
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {

            when(screenState){


                is MpesaPaymentViewModel.MpesaScreenState.Error -> {
                    Toast.makeText(context,
                        (screenState as MpesaPaymentViewModel.MpesaScreenState.Error).message,Toast.LENGTH_SHORT).show()
                }
                is MpesaPaymentViewModel.MpesaScreenState.Initial -> {
                    MpesaPaymentContent(
                        sendStkPush = sendStkPush,
                        amount =amount
                    )
                }
                is MpesaPaymentViewModel.MpesaScreenState.Loading -> {
                    ScreenLoader()
                }

                is MpesaPaymentViewModel.MpesaScreenState.SUCCESS -> {
                    Log.d("STK","Sent stk successfully")
                    navigateToStatus.invoke((screenState as MpesaPaymentViewModel.MpesaScreenState.SUCCESS).response.toString())
                }
            }


        }

    }
}

@Composable
@Preview(showBackground = true)
fun PreviewMpesaScreen(){
MpesaPaymentScreen(
    mpesaPaymentViewModel = hiltViewModel(),
    "",
    navigateBack = {},
    {_ -> },
    {}
)
}