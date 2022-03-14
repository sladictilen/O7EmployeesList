package com.tilensladic.o7employeeslist.ui.presentation.employees_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tilensladic.o7employeeslist.R
import com.tilensladic.o7employeeslist.data.database.EmployeeData

@Composable
fun EmployeeListItem(
    employee: EmployeeData,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
            .height(40.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                if (employee.gender == "Male") {
                    Image(
                        painter = painterResource(id = R.drawable.male_icon),
                        contentDescription = "male"
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.female_icon),
                        contentDescription = "female"
                    )
                }
            }
            Column() {
                Text(text = employee.name, modifier = Modifier.padding(start = 5.dp))
            }
        }
    }
}