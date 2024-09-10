package com.lemu.pay.checkout.repository

import com.lemu.pay.checkout.data.models.Todo
import kotlinx.coroutines.flow.Flow

interface MyRepository {

    suspend fun doNetworkCall()

    suspend fun getTodos():Flow<Todo>

}