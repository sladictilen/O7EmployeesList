package com.tilensladic.o7employeeslist.util.components

import android.media.Image
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tilensladic.o7employeeslist.R

@Composable
fun ProfileImage(modifier: Modifier, image: Int = R.drawable.blank_profile_picture) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(100.dp),
        border = BorderStroke(width = 3.dp, color = MaterialTheme.colors.secondary)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "sad",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }


}
