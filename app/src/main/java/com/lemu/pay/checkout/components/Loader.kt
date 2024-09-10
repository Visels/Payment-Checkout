package com.lemu.pay.checkout.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.lemu.pay.checkout.ui.theme.bluePrimary

@Composable
fun ScreenLoader(){
    Column (
        modifier = Modifier.fillMaxSize()
            .background(color = Color.White.copy(alpha = 0.25f)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
    CircularProgressIndicator(
        color = bluePrimary
    )
    }
}