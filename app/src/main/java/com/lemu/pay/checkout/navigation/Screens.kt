package com.lemu.pay.checkout.navigation

sealed class Screens(val route: String) {
    object HomeScreen: Screens("standard_screen")
    object TransactionsScreen: Screens("transactions_screen")
//    object PrintScreen: Screens("print_screen")
    object Logout: Screens("log_out")
}