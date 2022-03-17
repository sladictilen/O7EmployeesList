package com.tilensladic.o7employeeslist.ui.presentation.analytics.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnalyticsItem(label: String, content: String) {
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(start = 10.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = label, color = Color.DarkGray, fontSize = 14.sp)
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = content, fontSize = 20.sp)
        }
    }
}