package com.tilensladic.o7employeeslist.ui.presentation.employee_profile.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.tilensladic.o7employeeslist.R

@Composable
fun GoogleHitItem(headerTitle: String, url: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column() {
            Text(text = headerTitle)
        }
        Column() {
            IconButton(
                onClick = {
                    /*TODO Open website*/
                },
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.open_link_icon),
                        contentDescription = "Open link"
                    )
                }
            )
        }
    }
}