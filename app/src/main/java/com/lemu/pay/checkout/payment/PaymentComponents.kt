package com.lemu.pay.checkout.payment

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.lemu.pay.checkout.R
import com.lemu.pay.checkout.ui.theme.androidGreen
import com.lemu.pay.checkout.ui.theme.bluePrimary
import com.lemu.pay.checkout.ui.theme.blueSecondary
import com.lemu.pay.checkout.ui.theme.darkSurface
import com.lemu.pay.checkout.ui.theme.poppins_light
import com.lemu.pay.checkout.ui.theme.poppins_medium
import com.lemu.pay.checkout.ui.theme.poppins_semi_bold

@Composable
fun PaymentTypeButton(
    name:String,
    onClick:()->Unit,
    icon:Int
){

    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        ) ,
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(vertical = 15.dp)
            .border(
                width = 1.dp,
                color = Color.Gray,
            )
    ) {



//            Image(
//                painter = painterResource(id = icon),
//                contentDescription = "",
//                modifier = Modifier
//                    .size(40.dp)
//                    .padding(horizontal = 15.dp)
//            )
        Icon(
            modifier = Modifier.padding(horizontal = 15.dp),
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = "",
            tint = Color.Unspecified)
            Text(text = name, fontSize = 15.sp)




    }
}

@Composable
@Preview(showBackground = true)
fun PreviewPaymentTypeButton(){
    PaymentTypeButton(name = "M-PESA Express", onClick = {},R.drawable.mpesa_logo)
}

@Composable
fun PaymentDetailsCard(amount:String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
    ) {
        Card(
            backgroundColor = darkSurface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
        ) {
            Column (
                modifier = Modifier.padding(16.dp)
            ){
                Text(
                    text = "Payment Summary",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                PaymentDetail("Sub-total", amount.plus(".00"), color = Color.Gray)
                PaymentDetail("Processing", "0.00", color = Color.Gray)
                PaymentDetail("Total", "Ksh.".plus(amount.plus(".00")), color = Color.Black)
            }

        }
    }
}

@Composable
fun PaymentDetail(
    key:String,
    value:String,
    color:Color
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = key,
            style = MaterialTheme.typography.bodyLarge,
            color = color)

        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = color)
    }

}



@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PaymentAppBar(title: String, onNavigationClick: () -> Unit) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = bluePrimary
        ),
        title = { androidx.compose.material3.Text(
                    title,
            color = Color.White,
            fontFamily = poppins_semi_bold) },
        navigationIcon = {
            IconButton(onNavigationClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White)
            }
        }
    )
}


@SuppressLint("UnrememberedMutableState")
@Composable
@Preview(showBackground = true)
fun PaymentUserDetailsInput(){

    val number = remember { mutableStateOf("")}
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
        label = { Text(text = "07XXXXXXXX", fontFamily = poppins_light)},
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
}

@Composable
fun PaymentOptions(
    mpesaClicked:()->Unit
) {
    var selected by remember { mutableStateOf(false) }
    var mpesaSelected by remember { mutableStateOf(false) }

Column {

    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color(0xFFE0F7FA),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(8.dp)
            .clickable {
                mpesaSelected = true
                selected = false
                mpesaClicked.invoke()
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            RadioButton(
                selected = mpesaSelected,
                onClick = {
                    mpesaSelected = true
                    selected = false
                    mpesaClicked.invoke()
                          },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color(0xFF00796B),
                    unselectedColor = androidGreen
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "MPESA Express",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    fontFamily = poppins_medium
                )
                Text(
                    text = "Mobile money",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontFamily = poppins_light
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.img_mpesa_logo), // Replace with your drawable resource
                    contentDescription = "Visa",
                    modifier = Modifier.size(65.dp),
                    tint = Color.Unspecified
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(8.dp))

    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color(0xFFE0F7FA),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(8.dp)
            .clickable {
                selected = true
                mpesaSelected = false
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            RadioButton(
                selected = selected,
                onClick = {
                    selected = true
                    mpesaSelected = false
                          },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color(0xFF00796B),
                    unselectedColor = androidGreen
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Credit & Debit cards",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    fontFamily = poppins_medium
                )
                Text(
                    text = "Tap or swipe",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontFamily = poppins_light
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_visa), // Replace with your drawable resource
                    contentDescription = "Visa",
                    modifier = Modifier.size(35.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_master_card), // Replace with your drawable resource
                    contentDescription = "MasterCard",
                    modifier = Modifier.size(35.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_maestro), // Replace with your drawable resource
                    contentDescription = "Maestro",
                    modifier = Modifier.size(35.dp),
                    tint = Color.Unspecified
                )
            }
        }
    }


}




}

@Composable
@Preview(showBackground = true)
fun MPESAExpressCardOption() {
    var selected by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color(0xFFE0F7FA),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(8.dp)
            .clickable { selected = !selected }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            RadioButton(
                selected = selected,
                onClick = { selected = !selected },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color(0xFF00796B),
                    unselectedColor = androidGreen
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "MPESA Express",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "Mobile money",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.img_mpesa_logo), // Replace with your drawable resource
                    contentDescription = "Visa",
                    modifier = Modifier.size(65.dp),
                    tint = Color.Unspecified
                )
            }
        }
    }
}