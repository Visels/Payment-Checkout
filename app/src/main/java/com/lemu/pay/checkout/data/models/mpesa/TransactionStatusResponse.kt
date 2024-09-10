package com.lemu.pay.checkout.data.models.mpesa

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransactionStatusResponse(
    val receiptNo:String? = null,
    val status:String? = null,
    val message:String? = null,
    val resultCode:String? = null,
    val amount:String? = null
):Parcelable

