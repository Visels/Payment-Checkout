package com.lemu.pay.checkout.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lemu.pay.checkout.ui.theme.bluePrimary
import com.lemu.pay.checkout.ui.theme.poppins_semi_bold


@Composable
fun LoginContent(
    login:(String,String)->Unit
){
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val tenant = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current;

    val passwordVisible = remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        OutlinedTextField(
            value = tenant.value ,
            onValueChange = {
                tenant.value = it
            },
            label = { Text(text = "Tenant") },
        )

        OutlinedTextField(
            value = username.value ,
            onValueChange = {
                username.value = it
            },
            label = { Text(text = "Username") },
        )

        OutlinedTextField(
            value = password.value ,
            onValueChange = {
                password.value = it
            },
            label = { Text(text = "Password") },
            visualTransformation = if(passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable {
                        passwordVisible.value = !passwordVisible.value
                    },
                    imageVector = Icons.Filled.Info,
                    contentDescription = "" )
            }
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(16.dp)
                ,
            onClick = {
                keyboardController?.hide()
                login.invoke(username.value,password.value)

                      },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = bluePrimary,
                contentColor = Color.White
            )) {

            Text(text = "Login", fontFamily = poppins_semi_bold, fontSize = 15.sp)
            
        }

    }
}


@Composable
@Preview(showBackground = true)
fun PreviewLoginContent(){
    LoginContent(
        login = {_,_ ->}
    )
}