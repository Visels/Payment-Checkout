package com.lemu.pay.checkout.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lemu.pay.checkout.ui.SNumberPad
import com.lemu.pay.checkout.ui.theme.bluePrimary
import com.lemu.pay.checkout.ui.theme.blueSecondary
import com.lemu.pay.checkout.ui.theme.poppins_medium


@Composable
fun HomeScreen(
    navigateToPayment:(String) -> Unit
) {
    val viewModel: CalculatorViewModel = viewModel()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 12.dp)
                .weight(0.1f),

        ) {

            BasicTextField(
                value = viewModel.input.ifBlank { "0" },
                onValueChange = {},
                textStyle = MaterialTheme.typography.headlineLarge.copy(
                    textAlign = TextAlign.End,
                    color = Color.Black,
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White),
                readOnly = true,
                maxLines = 1,
            )
        }

        Row (
            modifier = Modifier.weight(0.7f)
        ){
        SNumberPad(viewModel::updateInput)

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = bluePrimary,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(5.dp),
                onClick = { navigateToPayment.invoke(viewModel.result) },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "CHARGE",
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = poppins_medium
                )
            }
        }
    }
}