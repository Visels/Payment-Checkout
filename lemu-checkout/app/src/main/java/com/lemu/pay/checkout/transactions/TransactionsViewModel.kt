package com.lemu.pay.checkout.transactions

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lemu.pay.checkout.data.models.transactions.TransactionResponse
import com.lemu.pay.checkout.repository.CheckoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val checkoutRepository: CheckoutRepository
):ViewModel(){

    private val _transactionUIState = MutableStateFlow<TransactionsUIState>(TransactionsUIState.Loading)
    val transactionUIState : StateFlow<TransactionsUIState> = _transactionUIState

    init {
        this.loadTransactions()
    }


    private fun loadTransactions()= viewModelScope.launch {
        _transactionUIState.value = TransactionsUIState.Loading
        checkoutRepository.fetchTransactions()
            .catch {
                _transactionUIState.value = TransactionsUIState.Error(it.message.toString())
            }
            .collect{
                response ->
                Log.d("TRANSACTIONS", "Fetched transactions length: ${response.size}")
                _transactionUIState.value = TransactionsUIState.Success(response)
            }

    }


    sealed class TransactionsUIState{

        object Loading: TransactionsUIState()
        class Error(val message:String):TransactionsUIState()
        class Success(val response: List<TransactionResponse?>):TransactionsUIState()
    }

}