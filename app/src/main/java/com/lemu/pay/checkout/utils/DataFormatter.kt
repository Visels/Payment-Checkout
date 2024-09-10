package com.lemu.pay.checkout.utils

fun formatPhoneNumber(phoneNo:String):String{

    if(phoneNo.startsWith("0")) return phoneNo

    if(phoneNo.startsWith("254")){
        return "0${phoneNo.substring(3,phoneNo.length)}"
    }

    if (phoneNo.startsWith("7")){
        return "0${phoneNo}"
    }
    return phoneNo
}