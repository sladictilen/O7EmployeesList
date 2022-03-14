package com.tilensladic.o7employeeslist.util.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomToggleButton(
    selected: String,
    selections: List<String>,
    toggleable: Boolean,
    onSelectedChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 2.dp,
                color = MaterialTheme.colors.secondary,
                shape = RoundedCornerShape(8.dp),
            )
            .height(intrinsicSize = IntrinsicSize.Min)
    ) {
        selections.forEachIndexed { _, listItem ->
            val isCurrentSelected = listItem == selected
            val backgroundColor =
                if (isCurrentSelected) MaterialTheme.colors.secondary else Color.Transparent
            val textColor =
                if (isCurrentSelected) MaterialTheme.colors.onSecondary else MaterialTheme.colors.onPrimary
            Text(
                text = listItem,
                color = textColor,
                modifier = Modifier
                    .background(backgroundColor)
                    .fillMaxHeight()
                    .padding(10.dp)
                    .toggleable(
                        value = isCurrentSelected,
                        enabled = toggleable,
                        onValueChange = { if (it) onSelectedChange(listItem) }
                    )
            )

        }
    }
}