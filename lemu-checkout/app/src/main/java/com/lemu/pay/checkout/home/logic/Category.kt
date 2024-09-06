package com.lemu.pay.checkout.home.logic

import androidx.annotation.DrawableRes
import com.ithoughts.dev.g3.calculator.R


sealed class Category(val factor: Double, val name: String, @DrawableRes val res: Int? = null) {


    object HomeScreen : Category(0.0, "Home", R.drawable.ic_outline_calculate_24)
    object ReprintScreen : Category(0.0, "Reprint", R.drawable.ic_baseline_functions_24)
    object TransactionsScreen : Category(0.0, "Transactions", R.drawable.ic_baseline_code_24)
    object LogoutScreen:Category(0.0, "Logout",R.drawable.ic_baseline_electrical_services_24)

    companion object {
        fun calculators() = listOf(
            HomeScreen,
            ReprintScreen,
            TransactionsScreen,
            LogoutScreen
        )
    }
}