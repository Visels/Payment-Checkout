package com.lemu.pay.checkout.mpesa_status

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lemu.pay.checkout.R
import com.lemu.pay.checkout.ui.theme.BlueGray
import com.lemu.pay.checkout.ui.theme.bluePrimary
import com.lemu.pay.checkout.ui.theme.poppins
import com.lemu.pay.checkout.ui.theme.poppins_medium
import com.lemu.pay.checkout.ui.theme.poppins_regular
import com.lemu.pay.checkout.ui.theme.poppins_semi_bold

@Composable
fun MpesaStatusContent(
    stkStatus: MpesaStatusViewModel.STKStatus?,
    checkStatus:()->Unit,
    navigateHome:()->Unit
){
    when(stkStatus){
        is MpesaStatusViewModel.STKStatus.Completed -> SuccessfulTransaction(stkStatus.amount,navigateHome)
        is MpesaStatusViewModel.STKStatus.Failed -> FailedTransaction(navigateHome)
        is MpesaStatusViewModel.STKStatus.Pending -> InitialScreen(checkStatus)
        null -> TODO()
    }
}


@Composable
fun InitialScreen(
    checkStatus: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(150.dp),
                    painter = painterResource(id = R.drawable.mobile_money_operation),
                    contentDescription ="" )

                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    text = "MPESA STK Sent to Customer",
                    fontFamily = poppins_semi_bold,
                    fontWeight = FontWeight.Normal,
                    fontSize = 17.sp,
                    style = TextStyle(
                        color = BlueGray
                    )

                )
            }


        }
        Spacer(modifier = Modifier.height(150.dp))
        Row {
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = bluePrimary,
                    contentColor = Color.White
                ),
                onClick = {checkStatus.invoke()},
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)) {
                Text(
                    text = "Check Status",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontFamily = poppins_regular
                )
            }
        }

    }
}


@Composable
@Preview(showBackground = true)
fun PreviewSuccessfulTransaction(){
    SuccessfulTransaction(amount = "2500.00") {

    }
}




@Composable
fun SuccessfulTransaction(
    amount:String
    ,navigateHome: () -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(150.dp),
                    painter = painterResource(id = R.drawable.check_mark),
                    contentDescription ="" )

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "KES.$amount",
                    fontFamily = poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Transaction completed successfully",
                    fontFamily = poppins_semi_bold,
                    fontWeight = FontWeight.Normal,
                    fontSize = 17.sp,
                    style = TextStyle(
                        color = BlueGray
                    )

                )
            }


        }
        Spacer(modifier = Modifier.height(150.dp))
        Row (modifier = Modifier.padding(vertical = 16.dp)){
            Button(

                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = bluePrimary
                ),
                onClick = {

                },
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .border(
                        width = 1.dp,
                        color = bluePrimary,
                        shape = RoundedCornerShape(30.dp)
                    )) {

                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_printer),
                    contentDescription = "",
                    tint = bluePrimary)

                Text(
                    text = "Print Receipt",
                    fontSize = 18.sp,
                    color = bluePrimary,
                    fontFamily = poppins_regular
                )
            }
        }

        Row {
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = bluePrimary,
                    contentColor = Color.White
                ),
                onClick = {navigateHome.invoke()},
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)) {
                Text(
                    text = "Done",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontFamily = poppins_regular
                )
            }
        }


    }
}




@Composable
fun FailedTransaction(navigateHome: () -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(150.dp),
                    painter = painterResource(id = R.drawable.failed_transaction),
                    contentDescription ="" )

                Text(
                    text = "Transaction processing failed",
                    fontFamily = poppins_semi_bold,
                    fontWeight = FontWeight.Normal,
                    fontSize = 17.sp,
                    style = TextStyle(
                        color = BlueGray
                    )
                )
            }


        }
        Spacer(modifier = Modifier.height(150.dp))
        Row {
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = bluePrimary,
                    contentColor = Color.White
                ),
                onClick = {navigateHome.invoke()},
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)) {
                Text(
                    text = "Done",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontFamily = poppins_regular
                )
            }
        }

    }
}


@Composable
fun TransactionCompletedSuccessfully(
    done:() -> Unit,
    title:String,
    message:String,
    icon:Int,
    buttonText:String,
    receiptNo:String,
    amount:String,
    date:String
){

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center)

        {

            Image(
                painter = painterResource(id = icon),
                contentDescription = "image",
                modifier = Modifier.size(120.dp))

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Ksh." + amount + ".00",
                fontFamily = poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )

            Spacer(modifier = Modifier.height(20.dp))


            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = message,
                fontFamily = poppins_semi_bold,
                fontWeight = FontWeight.Normal,
                fontSize = 17.sp,
                style = TextStyle(
                    color = BlueGray
                )

            )

            Spacer(modifier = Modifier.height(55.dp))


            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = bluePrimary,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(15.dp),
                onClick = {
                    done.invoke()
                },
                modifier = Modifier
                    .fillMaxWidth()
//                    .height(30.dp)
                    .padding(horizontal = 10.dp, vertical = 55.dp)) {
                Text(
                    text = buttonText,
                    fontSize = 15.sp,
                    fontFamily = poppins,
                    color = Color.White
                )
            }
//            TransactionDetailsItem("Receipt No", receiptNo)
//            TransactionDetailsItem("Date", date)
//            TransactionDetailsItem("Payment Method", "M-PESA")
//            TransactionDetailsItem("Amount", "Ksh."+amount)
//            TransactionDetailsItem("Note", "loan repayment")
        }


}

//@Composable
//@Preview(showBackground = true)
//fun PreviewSuccessfulTransaction(){
//    TransactionCompletedSuccessfully(
//        done = { /*TODO*/ },
//        title = "COMPLETED",
//        message = "Transaction completed successfully" ,
//        icon = R.drawable.check_mark ,
//        buttonText = "Done",
//        receiptNo = "AHDUR7581J",
//        amount = "2500",
//        date = "23rd August 2024"
//    )
//}

@Composable
fun TransactionDetailsItem(key:String, value:String){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = key,
            fontFamily = poppins_medium,
            style = TextStyle(
//                color = MyColors.BlueGray
            )
        )

        Text(
            text = value,
            fontFamily = poppins_medium,
            style = TextStyle(
//                color = MyColors.ValueColor
            )
        )

    }
}