package com.lemu.pay.checkout.repository

import com.lemu.pay.checkout.data.models.AuthenticationRequest
import com.lemu.pay.checkout.data.models.AuthenticationResponse
import com.lemu.pay.checkout.data.models.mpesa.CheckoutInitiateSTKRequest
import com.lemu.pay.checkout.data.models.mpesa.TransactionStatusResponse
import com.lemu.pay.checkout.data.models.transactions.InitiateSTKResponse
import com.lemu.pay.checkout.data.models.transactions.TransactionResponse
import kotlinx.coroutines.flow.Flow

interface CheckoutRepository {

    suspend fun login(request:AuthenticationRequest): Flow<AuthenticationResponse?>

    suspend fun initiateSTK(request:CheckoutInitiateSTKRequest): Flow<InitiateSTKResponse>

    suspend fun fetchTransactions(): Flow<List<TransactionResponse?>>

    suspend fun fetchTransactionStatus(transactionId:String): Flow<TransactionStatusResponse>

}