package com.lemu.pay.checkout.data.models.transactions

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InitiateSTKResponse(
    val transactionId:String?
):Parcelable
