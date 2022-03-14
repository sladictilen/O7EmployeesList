package com.tilensladic.o7employeeslist.ui.presentation.add_edit_employee

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tilensladic.o7employeeslist.R
import com.tilensladic.o7employeeslist.util.UiEvent
import com.tilensladic.o7employeeslist.util.components.CustomToggleButton
import com.tilensladic.o7employeeslist.util.components.ProfileImage

@Composable
fun AddEditEmployeeScreen(
    viewModel: AddEditEmployeeViewModel = hiltViewModel(),
    onPopBackStack: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                else -> {}
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Add new employee") },
                navigationIcon = {
                    IconButton(onClick = { viewModel.onEvent(AddEditEmployeeEvent.OnBackOrCancelClick) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back_icon),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalArrangement = Arrangement.Center
            ) {
                ProfileImage(modifier = Modifier.size(150.dp))
            }
            // Name
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                horizontalArrangement = Arrangement.Center
            ) {
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
            // Gender
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 5.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                CustomToggleButton(
                    selected = viewModel.gender,
                    selections = listOf("Male", "Female"),
                    toggleable = true,
                    onSelectedChange = {
                        viewModel.onEvent(AddEditEmployeeEvent.OnGenderValueChange(it))
                    }
                )
            }
            // Birthday date
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 5.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                // TODO implement datepicker
                TextField(
                    value = viewModel.birthday,
                    onValueChange = {
                        viewModel.onEvent(
                            AddEditEmployeeEvent.OnBirthdayValueChange(
                                it
                            )
                        )
                    },
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
            // Salary
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 5.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = viewModel.salary,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        when (it.toDoubleOrNull()) {
                            null -> viewModel.salary    // Old Value
                            else -> viewModel.onEvent(
                                AddEditEmployeeEvent.OnSalaryValueChange(
                                    it
                                )
                            ) // New Value
                        }
                    },
                    label = { Text("Salary", color = MaterialTheme.colors.secondary) },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = MaterialTheme.colors.secondary,
                        focusedIndicatorColor = MaterialTheme.colors.secondary,
                        unfocusedIndicatorColor = MaterialTheme.colors.secondary
                    ),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.euro_icon),
                            contentDescription = "Eur",
                            tint = MaterialTheme.colors.secondary
                        )
                    }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Column(modifier = Modifier.padding(5.dp)) {
                    Button(
                        onClick = { viewModel.onEvent(AddEditEmployeeEvent.OnBackOrCancelClick) },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
                    ) {
                        Text(text = "Cancel")
                    }
                }

                Column(modifier = Modifier.padding(5.dp)) {
                    Button(
                        onClick = { viewModel.onEvent(AddEditEmployeeEvent.OnAddEmployeeClick) },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
                    ) {
                        Text(text = "Add Employee")
                    }
                }

            }

        }

    }
}
