package com.lemu.pay.checkout.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lemu.pay.checkout.ui.theme.bluePrimary
import com.lemu.pay.checkout.ui.theme.poppins_regular
import com.lemu.pay.checkout.ui.theme.poppins_semi_bold
import com.lemu.pay.checkout.ui.theme.surfaceColor
import com.lemu.pay.checkout.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PaymentScreen(
    amount:String,
    navigateBack:()->Unit,
    navigateToMpesaPayment:()->Unit
){

    Scaffold(
        containerColor = surfaceColor,
        modifier = Modifier.fillMaxSize(),
        topBar = {PaymentAppBar(title ="Payment", onNavigationClick = navigateBack)}
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

//            PaymentDetailsCard(amount)

            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 50.dp,
                        end = 25.dp,
                    )
            ){
                Text(text = "KES.", fontSize = 35.sp, fontFamily = poppins_regular)
                Text(text = amount.plus(".00"), fontSize = 35.sp, fontFamily = poppins_regular)
            }

            Spacer(modifier = Modifier.height(55.dp))

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ){
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    
                    Text(
                        text = "Please select a payment method:",
                        fontSize = 18.sp,
                        fontFamily = poppins_regular
                    )
                }
            }


            PaymentOptions(
                mpesaClicked = navigateToMpesaPayment
            )

            Spacer(modifier = Modifier.height(20.dp))

//            Row (
//                modifier = Modifier.fillMaxWidth(),
//            ){
//                Column {
//                    Text(
//                        text = "Enter customer phone number",
//                        fontSize = 18.sp)
//
//                    PaymentUserDetailsInput()
//
//                    Spacer(modifier = Modifier.height(50.dp))
//
//                    Button(
//                        colors = ButtonDefaults.buttonColors(
//                            backgroundColor = bluePrimary,
//                            contentColor = Color.White
//
//                        ),
//                        onClick = {  },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(60.dp)) {
//                        Text(text = "Pay", fontSize = 18.sp)
//                    }
//                }
//            }




        }

    }

    }

@Composable
@Preview(showBackground = true)
fun PreviewPaymentScreen(){
    PaymentScreen(amount = "2000", navigateBack = {}, navigateToMpesaPayment = {})
}
