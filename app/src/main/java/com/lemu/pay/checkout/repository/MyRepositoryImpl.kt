package com.lemu.pay.checkout.repository

import android.app.Application
import com.lemu.pay.checkout.R
import com.lemu.pay.checkout.data.api.CheckoutApi
import com.lemu.pay.checkout.data.models.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MyRepositoryImpl (
    private val api:CheckoutApi,
    private val app:Application
): MyRepository {

    init {
        val appName = app.getString(R.string.app_name)
        println("Hello from $appName")
    }

    override suspend fun doNetworkCall() {
        api.doNetworkCall()
    }

    override suspend fun getTodos(): Flow<Todo> {
      return  flow {
            emit(api.getTodos())
        }
    }
}