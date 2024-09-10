package com.lemu.pay.checkout.payment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lemu.pay.checkout.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor (
    private val repository: MyRepository
):ViewModel() {

    fun doNetworkCall() = viewModelScope.launch {
        repository.doNetworkCall()
    }




}