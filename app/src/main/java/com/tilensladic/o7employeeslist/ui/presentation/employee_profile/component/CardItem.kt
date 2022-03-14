package com.tilensladic.o7employeeslist.ui.presentation.employee_profile.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tilensladic.o7employeeslist.R

@Composable
fun CardItem(icon: Int, label: String, content: String) {
    Row(modifier = Modifier.height(50.dp).fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.birthday_icon),
                contentDescription = "Birthday"
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(start = 10.dp)
        ) {
            Row() {
                Text(text = label, color = Color.LightGray, fontSize = 14.sp)
            }
            Row() {
                Text(text = content, fontSize = 20.sp)
            }
        }
    }
}