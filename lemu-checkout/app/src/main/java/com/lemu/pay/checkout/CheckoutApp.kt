@file:OptIn(ExperimentalMaterial3Api::class)

package com.lemu.pay.checkout

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.lemu.pay.checkout.home.HomeScreen
import com.lemu.pay.checkout.home.logic.Category
import com.lemu.pay.checkout.navigation.MainDrawer
import com.lemu.pay.checkout.navigation.Screens
import com.lemu.pay.checkout.print.PrintScreen
import com.lemu.pay.checkout.transactions.TransactionsScreen
import com.lemu.pay.checkout.ui.CheckoutAppbar
import com.lemu.pay.checkout.ui.theme.surfaceColor
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CheckoutApp(
    navigateToPayment: (String) -> Unit,
    logout:()-> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var selectedCategory by remember { mutableStateOf<Category>(Category.HomeScreen) }
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()


    ModalNavigationDrawer(drawerContent = {
        MainDrawer(
            selected = selectedCategory,
            onCategoryClick = {
                scope.launch {
                    drawerState.close()
                    val destination = when(it) {
                        is Category.HomeScreen -> Screens.HomeScreen.route
                        is Category.TransactionsScreen -> Screens.TransactionsScreen.route
                        is Category.ReprintScreen -> Screens.PrintScreen.route
                        is Category.LogoutScreen -> Screens.Logout.route
                        else -> Screens.HomeScreen.route
                    }

                    if(destination.equals(Screens.Logout.route)){
                        logout.invoke()
                    }else {
                        navController.navigate(destination, navOptions = navOptions {
                            popUpTo(0)
                        })
                    }

                }
            }
        )
    }, drawerState = drawerState) {
        Scaffold(
            containerColor = surfaceColor,
            topBar = {
            CheckoutAppbar(selectedCategory.name) {
                scope.launch { drawerState.open() }
            }
        }) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Screens.HomeScreen.route,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(Screens.HomeScreen.route) {
                    selectedCategory = Category.HomeScreen
                    HomeScreen(navigateToPayment)
                }
                composable(Screens.TransactionsScreen.route) {
                    selectedCategory = Category.TransactionsScreen
                    TransactionsScreen()
                }
                composable(Screens.PrintScreen.route) {
                    selectedCategory = Category.ReprintScreen
                    PrintScreen()
                }
                composable(Screens.Logout.route){
                    selectedCategory = Category.LogoutScreen
                    logout.invoke()
                }
//                composable("${Screens.ConverterScreen.route}/{category}", arguments = listOf(
//                    navArgument("category") { type = NavType.StringType }
//                )) { stackEntry ->
//                    stackEntry.arguments?.getString("category")?.let { s ->
//                        selectedCategory = Category.values().first { it.name == s }
//                        ConversionScreen(s)
//                    }
//                }
            }
        }
    }
}

