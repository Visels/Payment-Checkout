package com.lemu.pay.checkout.mpesa

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lemu.pay.checkout.data.models.mpesa.CheckoutInitiateSTKRequest
import com.lemu.pay.checkout.repository.CheckoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MpesaPaymentViewModel @Inject constructor(
    private val checkoutRepository: CheckoutRepository
):ViewModel() {

    var _uiScreenState = MutableStateFlow<MpesaScreenState>(MpesaScreenState.Initial)

    val uiScreenState:StateFlow<MpesaScreenState> = _uiScreenState


    lateinit var transactionId: String




    fun initiateSTK(mobile:String,amount:String) = viewModelScope.launch {
        _uiScreenState.value = MpesaScreenState.Loading
        val request = CheckoutInitiateSTKRequest(
            amount = amount,
            mobileNo = mobile,
            wallet = "110000008",
            merchantId = "exelient"
        )
        checkoutRepository.initiateSTK(request)
            .catch {
                Log.d("STK ERROR", it.message.toString())
                _uiScreenState.value = MpesaScreenState.Error(it.message.toString())
            }
            .collect{
                Log.d("STK RESPONSE", it.toString())
                if (it.transactionId != null) {
                    transactionId = it.transactionId
                    _uiScreenState.value = MpesaScreenState.SUCCESS(transactionId)
                }
                Log.d("MPESA STK", "Sent with transaction id $transactionId")
            }
    }


    sealed class MpesaScreenState () {
        object Initial:MpesaScreenState()
        object Loading:MpesaScreenState()
        class Error(val message:String): MpesaScreenState()
        class SUCCESS(val response:String?):MpesaScreenState()
    }
}