package com.lemu.pay.checkout.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Todo(
    val userId:Long,
    val id:Long,
    val title:String,
    val completed:Boolean
):Parcelable


