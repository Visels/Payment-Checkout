package com.lemu.pay.checkout.mpesa

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ithoughts.dev.g3.calculator.R
import com.lemu.pay.checkout.payment.PaymentAppBar
import com.lemu.pay.checkout.payment.PaymentUserDetailsInput
import com.lemu.pay.checkout.ui.theme.bluePrimary
import com.lemu.pay.checkout.ui.theme.blueSecondary
import com.lemu.pay.checkout.ui.theme.poppins_light
import com.lemu.pay.checkout.ui.theme.poppins_medium
import com.lemu.pay.checkout.ui.theme.poppins_regular
import com.lemu.pay.checkout.ui.theme.poppins_semi_bold


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MpesaPaymentScreen(
//    mpesaPaymentViewModel: MpesaPaymentViewModel = viewModel(),
    amount:String?,
    navigateBack:()->Unit,
    sendStkPush:(String)->Unit
){
//    val uiScreenState = mpesaPaymentViewModel.uiScreenState.collectAsState()
    val number = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
        PaymentAppBar(
            title = "MPESA",
            onNavigationClick = navigateBack)
        }
    ) {

        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "Total Amount",
                        fontFamily = poppins_semi_bold,
                        color = Color.Gray,
                        fontSize = 15.sp
                    )
                    Text(
                        text = "KES."+amount+".00",
                        fontFamily = poppins_semi_bold,
                        fontSize = 24.sp
                    )
                }
            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Image(
                    imageVector = ImageVector.vectorResource(
                        id = R.drawable.sample_qr_code),
                    contentDescription = "qr_code",
                    modifier = Modifier.size(300.dp))
            }



            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Scan with your mpesa app to pay",
                    fontFamily = poppins_regular,
                    fontSize = 15.sp
                )

            }

            Spacer(modifier = Modifier.height(18.dp))

            Row (
                modifier = Modifier.fillMaxWidth(),
            ){
                Column {
                    Text(
                        text = "Enter customer phone number",
                        fontSize = 15.sp,
                        fontFamily = poppins_medium)

//                    PaymentUserDetailsInput()
                    OutlinedTextField(
                        value = number.value,
                        onValueChange = {
                            number.value = it
                        },
                        textStyle = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = poppins_medium,
                            letterSpacing = 3.sp
                        ),
                        maxLines = 1,
                        shape = RoundedCornerShape(15.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = { androidx.compose.material.Text(text = "07XXXXXXXX", fontFamily = poppins_light) },
                        leadingIcon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.mobile_svgrepo_com),
                                contentDescription =""
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = bluePrimary,
                            focusedLabelColor = blueSecondary
                        )
                    )

                    Spacer(modifier = Modifier.height(50.dp))

                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = bluePrimary,
                            contentColor = Color.White
                        ),
                        onClick = {
                                  sendStkPush.invoke(number.value)
                        },
                        shape = RoundedCornerShape(30.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)) {
                        Text(
                            text = "Send",
                            fontSize = 18.sp,
                            color = Color.White,
                            fontFamily = poppins_regular)
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewMpesaScreen(){
MpesaPaymentScreen(
    "",
    navigateBack = {},
    {_ -> }
)
}