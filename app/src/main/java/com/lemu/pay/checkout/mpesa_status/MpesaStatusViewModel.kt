package com.lemu.pay.checkout.mpesa_status

import android.net.http.UrlRequest.Status
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lemu.pay.checkout.repository.CheckoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MpesaStatusViewModel @Inject constructor(
    private val checkoutRepository: CheckoutRepository
):ViewModel() {

    val _statusUIState = MutableStateFlow<StatusUIState>(StatusUIState.Initial)
    val statusUIState:StateFlow<StatusUIState> = _statusUIState


    val _stkStatus = MutableStateFlow<STKStatus>(STKStatus.Pending)
    val stkStatus:StateFlow<STKStatus> = _stkStatus


    fun fetchTransactionStatus(transactionId:String) = viewModelScope.launch {
        _statusUIState.value = StatusUIState.Loading
        checkoutRepository.fetchTransactionStatus(transactionId)
            .catch {

            }
            .collect{

                if(it.status.equals("200")){
                    _stkStatus.value = STKStatus.Completed(it.amount.toString())
                }

                if(it.status.equals("400")){
                    _stkStatus.value = STKStatus.Failed("Processing failed")
                }

                if(it.status.equals("150")){
                    _stkStatus.value = STKStatus.Pending
                }
                _statusUIState.value = StatusUIState.Success

            }

    }


    sealed class StatusUIState {
        object Initial : StatusUIState()

        object Loading : StatusUIState()

        object Success : StatusUIState()

        class Error(val message:String) : StatusUIState()
    }

    sealed class STKStatus{
        object Pending: STKStatus()

        class Completed(val amount:String): STKStatus()

        class Failed(val message:String) : STKStatus()
    }


}