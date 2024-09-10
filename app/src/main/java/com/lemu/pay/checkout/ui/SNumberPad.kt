package com.lemu.pay.checkout.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lemu.pay.checkout.R
import com.lemu.pay.checkout.ui.theme.bluePrimary

@Composable
fun SNumberPad(onKeyPress: (String) -> Unit) {
    ElevatedCard(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.elevatedCardElevation(0.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxHeight(),
            columns = GridCells.Fixed(4),
            contentPadding = PaddingValues(4.dp),
            userScrollEnabled = false
        ) {
            "C / back % 7 8 9 * 4 5 6 - 1 2 3 + +/- 0 .".split(" ").forEach { key ->
                item {
                    CVNumberKey(key,enabled = if (key == "." || key == "%") false else true, onKeyPress)
                }
            }
            item {
                Button(
                    onClick = { onKeyPress("=") },
                    contentPadding = PaddingValues(vertical = 18.dp),
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier.fillMaxSize(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = bluePrimary
                    )
                ) {
                    Text("=", fontFamily = FontFamily.SansSerif, fontSize = 20.sp)
                }
            }

        }
    }
}

@Composable
fun CVNumberKey(
    key: String,
    enabled: Boolean = true,
    onClick: (String) -> Unit
) {
    TextButton(
        enabled = enabled,
        onClick = { onClick(key) },
        shape = RoundedCornerShape(1.dp),
        contentPadding = PaddingValues(vertical = 0.dp),
        modifier = Modifier
            .fillMaxSize()
//            .border(
//                width = 1.dp,
//                shape = MaterialTheme.shapes.small,
//                color = Color.Black
//            )
    ) {
        when (key) {
            "back" -> Icon(
                ImageVector.vectorResource(R.drawable.ic_round_backspace_24),
                "back",
                tint = MaterialTheme.colorScheme.secondary
            )
            else -> Text(key, fontFamily = FontFamily.SansSerif, fontSize = 29.sp, color = Color.Black)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewCvNumberKey(){
    CVNumberKey(key = "1") {
        
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewNumPad(){
    SNumberPad {

    }
}