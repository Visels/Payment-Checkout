package com.lemu.pay.checkout.transactions

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lemu.pay.checkout.components.ScreenLoader
import com.lemu.pay.checkout.home.logic.Category

@Composable
fun TransactionsScreen(
    viewModel: TransactionsViewModel = hiltViewModel()
){
    val uiState by viewModel.transactionUIState.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        when(uiState){
            is TransactionsViewModel.TransactionsUIState.Loading -> {
                    ScreenLoader()
            }

            is TransactionsViewModel.TransactionsUIState.Success -> {
                TransactionsContent(transactions = (uiState as TransactionsViewModel.TransactionsUIState.Success).response)
            }

            is TransactionsViewModel.TransactionsUIState.Error -> {
                Toast.makeText(context,
                    (uiState as TransactionsViewModel.TransactionsUIState.Error).message,Toast.LENGTH_SHORT).show()
            }
        }


    }
}