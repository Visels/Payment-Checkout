package com.lemu.pay.checkout.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthenticationResponse(
    val username:String,
    val userId:Long,
    val base64EncodedAuthenticationKey:String,
    val authenticated:Boolean,
    val officeId:Long,
    val officeName:String
):Parcelable


//{
//    "username": "wayaapi",
//    "userId": 4,
//    "base64EncodedAuthenticationKey": "d2F5YWFwaToxMjM0",
//    "authenticated": true,
//    "officeId": 2,
//    "officeName": "Waya Head Office",
//    "roles": [
//    {
//        "id": 3,
//        "name": "MERCHANT_USER",
//        "description": "merchant user",
//        "disabled": false
//    }
//    ],
//    "permissions": [
//    "CREATE_PAYMENT"
//    ],
//    "shouldRenewPassword": false,
//    "isTwoFactorAuthenticationRequired": false
//}
