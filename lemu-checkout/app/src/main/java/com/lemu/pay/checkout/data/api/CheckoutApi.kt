package com.lemu.pay.checkout.data.api

import com.lemu.pay.checkout.data.models.AuthenticationRequest
import com.lemu.pay.checkout.data.models.AuthenticationResponse
import com.lemu.pay.checkout.data.models.Todo
import com.lemu.pay.checkout.data.models.mpesa.CheckoutInitiateSTKRequest
import com.lemu.pay.checkout.data.models.transactions.TransactionResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface CheckoutApi {

    @GET("home")
    suspend fun doNetworkCall()

    @GET("todos/1")
    suspend fun getTodos():Todo

    @POST("authentication")
    suspend fun login(@Body
    authBody:AuthenticationRequest
    ):AuthenticationResponse


    @POST("checkout/initiate-stk")
    suspend fun initiateSTK(
        @Body body:CheckoutInitiateSTKRequest
    ):String?

    @GET("checkout/fetch-transactions")
    suspend fun fetchTransactions():List<TransactionResponse?>

}