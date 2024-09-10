package com.lemu.pay.checkout.mpesa_status

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.lemu.pay.checkout.components.ScreenLoader
import com.lemu.pay.checkout.payment.PaymentAppBar

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MpesaStatusScreen(
    viewModel: MpesaStatusViewModel = hiltViewModel(),
    navigateBack:()->Unit,
    checkStatus:()->Unit,
    navigateToHome:()->Unit
)
{

    val screenState by viewModel.statusUIState.collectAsState()
    val stkStatus by viewModel.stkStatus.collectAsState()
    val context = LocalContext.current


    Scaffold(
        topBar = {
            PaymentAppBar(title = "MPESA", onNavigationClick = navigateBack)
        }

    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {

            Box {
                when(screenState){
                    is MpesaStatusViewModel.StatusUIState.Error -> {
                        Toast.makeText(context,
                            (screenState as MpesaStatusViewModel.StatusUIState.Error).message,Toast.LENGTH_SHORT).show()
                    }
                    MpesaStatusViewModel.StatusUIState.Initial -> {}
                    MpesaStatusViewModel.StatusUIState.Loading -> ScreenLoader()
                    MpesaStatusViewModel.StatusUIState.Success -> {}
                }
            }

            MpesaStatusContent(
                stkStatus,
                checkStatus,
                navigateToHome,
            )

        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewMpesaStatusScreen(){
    MpesaStatusScreen(
        viewModel = hiltViewModel(),
        navigateBack = { /*TODO*/ },
        navigateToHome = {},
        checkStatus = {}
        )
}