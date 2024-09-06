package com.lemu.pay.checkout.repository

import com.lemu.pay.checkout.data.api.CheckoutApi
import com.lemu.pay.checkout.data.models.AuthenticationRequest
import com.lemu.pay.checkout.data.models.AuthenticationResponse
import com.lemu.pay.checkout.data.models.mpesa.CheckoutInitiateSTKRequest
import com.lemu.pay.checkout.data.models.transactions.TransactionResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CheckoutRepositoryImpl (
    private val api:CheckoutApi
): CheckoutRepository {


    override suspend fun login(request: AuthenticationRequest): Flow<AuthenticationResponse?> {
        return flow {
            emit(api.login(request))
        }
    }

    override suspend fun initiateSTK(request: CheckoutInitiateSTKRequest): Flow<String?> {
        return flow {
            emit(api.initiateSTK(request))
        }
    }

    override suspend fun fetchTransactions(): Flow<List<TransactionResponse?>> {
        return flow {
            emit(api.fetchTransactions())
        }
    }
}