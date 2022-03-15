package com.tilensladic.o7employeeslist.ui.presentation.employee_profile.component

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tilensladic.o7employeeslist.R

@Composable
fun GoogleHitItem(headerTitle: String, url: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(3f)
        ) {
            Text(
                text = headerTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .horizontalScroll(
                        rememberScrollState()
                    )
                    .padding(start = 5.dp, end = 5.dp)
            )
        }
        val localUri = LocalUriHandler.current
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f),
            horizontalAlignment = Alignment.End
        ) {
            IconButton(
                onClick = {
                    localUri.openUri(url)
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
    Divider(Modifier.fillMaxWidth().height(1.dp), color = MaterialTheme.colors.secondary)
}