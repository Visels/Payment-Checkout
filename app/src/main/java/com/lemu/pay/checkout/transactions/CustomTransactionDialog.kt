package com.lemu.pay.checkout.transactions

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.lemu.pay.checkout.ui.theme.BlueGray
import com.lemu.pay.checkout.ui.theme.ValueColor
import com.lemu.pay.checkout.ui.theme.bluePrimary
import com.lemu.pay.checkout.ui.theme.poppins
import com.lemu.pay.checkout.ui.theme.poppins_regular
import com.lemu.pay.checkout.R

@Composable
fun CustomDialog(
    onComplete:()->Unit,
    receiptNo:String,
    paymentMethod:String,
    date:String,
    note:String,
    amount:String,
    printReceipt: ()->Unit
){
    Dialog(
        onDismissRequest = { onComplete.invoke() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {

        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier.fillMaxWidth(0.90f),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Image(painter = painterResource(
                    id = R.drawable.check_mark),
                    contentDescription = "",
                    modifier = Modifier.size(60.dp))
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Completed",
                    fontFamily = poppins,
                    fontSize = 18.sp,
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "KES." +amount,
                    fontFamily = poppins,
                    fontSize = 17.sp,
                    color = BlueGray)
                Spacer(modifier = Modifier.height(10.dp))
//                HorizontalDivider()
                Spacer(modifier = Modifier.height(10.dp))
                TransactionRow(key = "Receipt", value = receiptNo )
                TransactionRow(key = "Payment", value = paymentMethod)
                TransactionRow(key = "Date", value = date)
                TransactionRow(key = "Mobile", value = note)

                Spacer(modifier = Modifier.height(25.dp))

            Row {
                Button(
                    shape = RoundedCornerShape(25.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = bluePrimary,
                            shape = RoundedCornerShape(25.dp)
                        )
                        .height(40.dp)
                        .weight(1f)
                        ,
                    onClick = { printReceipt.invoke() },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White
                    )) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_printer),
                        contentDescription = "",
                        tint = bluePrimary)
                    Text(
                        text = "Print",
                        fontFamily = poppins_regular,
                        fontSize = 15.sp,
                        color = bluePrimary)

                }
                
                Spacer(modifier = Modifier.width(5.dp))

                Button(
                    shape = RoundedCornerShape(25.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .weight(1f),
                    onClick = { onComplete.invoke() },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = bluePrimary
                    )) {
                    Text(
                        text = "Done",
                        fontFamily = poppins_regular,
                        fontSize = 15.sp,
                        color = Color.White)

                }
            }

            }

        }


    }

}

@Composable
@Preview(showBackground = true)
fun PreviewCustomDialog(){
    CustomDialog(
        onComplete = { /*TODO*/ },
        receiptNo = "AHFR6567D",
        paymentMethod = "M-PESA" ,
        date = "23-07-2024",
        note = "0723456194",
        amount = "2500",
        printReceipt = {}
    )
}

@Composable
fun TransactionRow(
    key:String,
    value:String
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 7.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = key,
            fontFamily = poppins,
            color = BlueGray,
            fontSize = 14.sp
        )
        Text(
            text = value,
            fontFamily = poppins_regular,
            color = ValueColor,
            fontSize = 14.sp
        )
    }
}