@file:OptIn(ExperimentalMaterial3Api::class)

package com.lemu.pay.checkout.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lemu.pay.checkout.home.logic.Category
import com.lemu.pay.checkout.ui.theme.blueLight
import com.lemu.pay.checkout.ui.theme.bluePrimary
import com.lemu.pay.checkout.R

@Composable
fun MainDrawer(
    selected: Category,
    onCategoryClick: (Category) -> Unit,
    ) {
    ModalDrawerSheet(modifier = Modifier.padding(end = 50.dp), drawerContainerColor = Color.White) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(
                    color = bluePrimary
                ),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "Lemu Checkout",
                color = Color.White,
                fontSize = 26.sp,
                modifier = Modifier.padding(16.dp))
        }
        LazyColumn(contentPadding = PaddingValues(12.dp, 12.dp)) {
//            item { AppTitle() }
            items(Category.calculators()) {
                NavItem(selected == it, it, onCategoryClick)
            }

        }


    }
}

@Composable
fun NavItem(selected: Boolean, category: Category, onClick: (Category) -> Unit) {
    NavigationDrawerItem(
        selected = selected,
        modifier = Modifier.fillMaxWidth(),
        onClick = { onClick(category) },
        label = { Text(category.name, style = MaterialTheme.typography.labelLarge) },
        shape = MaterialTheme.shapes.extraLarge,
        icon = {
            Icon(
                painter = painterResource(category.res ?: R.drawable.no_icon),
                tint = if (selected) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
                contentDescription = category.name,
                modifier = Modifier.size(24.dp)
            )
        },
        colors = NavigationDrawerItemDefaults.colors(
            unselectedContainerColor = Color.White,
            selectedContainerColor = blueLight,
            unselectedTextColor = Color.Black,
            selectedTextColor = Color.White,
            selectedIconColor = Color.White
        )
    )
}

@Composable
fun AppTitle() {
    Text(
        text = stringResource(id = R.string.app_name),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(16.dp),
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold
    )
}

@Composable
@Preview(showBackground = true)
fun PreviewMainDrawer(){
    MainDrawer(selected = Category.HomeScreen) {
        
    }
}