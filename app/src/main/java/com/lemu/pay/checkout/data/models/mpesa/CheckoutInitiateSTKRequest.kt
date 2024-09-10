package com.lemu.pay.checkout.data.models.mpesa

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CheckoutInitiateSTKRequest(
    val amount:String?,
    val wallet:String?,
    val mobileNo:String?,
    val merchantId:String?
):Parcelable
