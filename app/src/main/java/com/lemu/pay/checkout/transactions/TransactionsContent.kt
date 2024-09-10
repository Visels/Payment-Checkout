package com.lemu.pay.checkout.transactions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lemu.pay.checkout.R
import com.lemu.pay.checkout.data.models.transactions.TransactionResponse
import com.lemu.pay.checkout.ui.theme.poppins_regular
import com.lemu.pay.checkout.ui.theme.poppins_semi_bold
import com.lemu.pay.checkout.utils.formatPhoneNumber

@Composable
fun TransactionsContent(
    viewTransaction: (TransactionsViewModel.TransactionDetails) -> Unit,
    transactions:List<TransactionResponse?>
){
    LazyColumn {
       items(transactions){

           TransactionEntry(
               icon = R.drawable.img_mpesa_logo,
               iconColor = Color.Green ,
               transactionType = "Received",
               transactionNote = "Customer payment",
               amount = it?.amount.toString(),
               viewTransaction = viewTransaction,
               transaction = it
           )

       }
    }

}


@Composable
fun TransactionEntry(
    icon:Int,
    iconColor: Color,
    transactionType: String,
    transactionNote: String,
    amount: String,
    viewTransaction:(TransactionsViewModel.TransactionDetails) -> Unit,
    transaction:TransactionResponse?
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 5.dp)
            .clickable {
                val transaction = TransactionsViewModel.TransactionDetails(
                    receiptNo = transaction?.receiptNo.toString(),
                    amount = transaction?.amount.toString(),
                    date = transaction?.date.toString(),
                    paymentMethod = "M-PESA",
                    note = formatPhoneNumber(transaction?.customerName.toString())

                )
                       viewTransaction.invoke(transaction)
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(15.dp)
        ){



            Icon(
                painter = painterResource(id = icon),
                contentDescription ="",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 10.dp))

            Row(modifier = Modifier
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {


                Column {

                    Text(
                        text = transaction?.receiptNo.toString(),
                        fontFamily = poppins_semi_bold,
                        fontSize = 15.sp)




                    Text(
                        text = formatPhoneNumber(transaction?.customerName.toString()),
                        fontFamily = poppins_regular,
                        fontSize = 15.sp,
                        color = Color.Gray)



                }

                Text(
                    text = "KES."+amount,
                    fontFamily = poppins_semi_bold,
                    fontSize = 15.sp,
                    color = Color.Black
                )
            }

        }

    }
}

@Composable
@Preview(showBackground = true)
fun PreviewTransactionEntry(){
    TransactionEntry(
        icon = R.drawable.img_mpesa_logo,
        iconColor = Color.Green ,
        transactionType = "Received",
        transactionNote = "Customer payment",
        amount = "2500",
        viewTransaction = { /*TODO*/ },
        transaction = TransactionResponse(
            customerName = "0723456194",
            receiptNo = "AHDKFJFUTJ",
            date = "Customer payment"

        )
    )
}