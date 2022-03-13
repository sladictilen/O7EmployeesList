package com.tilensladic.o7employeeslist.ui.presentation.add_edit_employee

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tilensladic.o7employeeslist.R

@Composable
fun AddEditEmployeeScreen(
    viewModel: AddEditEmployeeViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Add new employee") }
            )
        }
    ) {
        Column(modifier = Modifier.padding(5.dp)) {
            Row() {

                TextField(
                    value = viewModel.name,
                    onValueChange = { viewModel.onEvent(AddEditEmployeeEvent.OnNameValueChange(it)) },
                    label = { Text("Name", color = MaterialTheme.colors.secondary) },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = MaterialTheme.colors.secondary,
                        focusedIndicatorColor = MaterialTheme.colors.secondary,
                        unfocusedIndicatorColor = MaterialTheme.colors.secondary
                    )
                )
            }
            Row() {
                TextField(
                    value = "",
                    onValueChange = { },
                    label = { Text("Birthday date", color = MaterialTheme.colors.secondary) },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = MaterialTheme.colors.secondary,
                        focusedIndicatorColor = MaterialTheme.colors.secondary,
                        unfocusedIndicatorColor = MaterialTheme.colors.secondary
                    ),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.calendar_icon),
                            contentDescription = "Calendar",
                            tint = MaterialTheme.colors.secondary
                        )
                    }
                )
            }
            Row() {
                Column(modifier = Modifier.padding(5.dp)) {
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
                    ) {
                        Text(text = "Cancel")
                    }
                }

                Column(modifier = Modifier.padding(5.dp)) {
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
                    ) {
                        Text(text = "Add Employee")
                    }
                }

            }

        }

    }
}
