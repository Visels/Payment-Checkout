@file:OptIn(ExperimentalMaterial3Api::class)

package com.lemu.pay.checkout.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lemu.pay.checkout.ui.theme.bluePrimary
import com.lemu.pay.checkout.ui.theme.poppins_semi_bold
import com.lemu.pay.checkout.ui.theme.surfaceColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutAppbar(title: String, onNavigationClick: () -> Unit) {
    CenterAlignedTopAppBar(

        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = bluePrimary
        ),
        title = { Text(title, color = Color.White, fontFamily = poppins_semi_bold) },
        navigationIcon = {
            IconButton(onNavigationClick) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = null,
                    tint = Color.White,
                    //todo:modifiable size
                    modifier = Modifier.size(30.dp))
            }
        }
    )
}