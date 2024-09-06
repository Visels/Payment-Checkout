package com.lemu.pay.checkout.repository

import com.lemu.pay.checkout.data.models.AuthenticationRequest
import com.lemu.pay.checkout.data.models.AuthenticationResponse
import com.lemu.pay.checkout.data.models.mpesa.CheckoutInitiateSTKRequest
import com.lemu.pay.checkout.data.models.transactions.TransactionResponse
import kotlinx.coroutines.flow.Flow

interface CheckoutRepository {

    suspend fun login(request:AuthenticationRequest): Flow<AuthenticationResponse?>

    suspend fun initiateSTK(request:CheckoutInitiateSTKRequest): Flow<String?>

    suspend fun fetchTransactions(): Flow<List<TransactionResponse?>>

}