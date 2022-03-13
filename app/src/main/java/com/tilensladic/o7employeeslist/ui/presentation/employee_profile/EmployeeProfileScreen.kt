package com.tilensladic.o7employeeslist.ui.presentation.employee_profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tilensladic.o7employeeslist.R
import com.tilensladic.o7employeeslist.util.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun EmployeeProfileScreen(
    viewModel: EmployeeProfileScreenViewModel = hiltViewModel(),
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
                title = { Text(text = "Employee Profile") },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.onEvent(EmployeeProfileEvent.OnBackPressed) },
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.back_icon),
                                contentDescription = "Go Back"
                            )
                        })
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Card(
                elevation = 5.dp,
                shape = RoundedCornerShape(3.dp),
                backgroundColor = MaterialTheme.colors.secondary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Column() {
                    Text(text = "Name: ${viewModel.employee?.name} ")
                }
            }
        }
    }

}