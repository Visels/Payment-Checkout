package com.lemu.pay.checkout.data.models.transactions

data class TransactionResponse(
    val receiptNo:String? = null,
    val customerName:String? = null,
    val amount:String? = null,
    val status:String? = null
)

