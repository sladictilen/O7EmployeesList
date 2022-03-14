package com.tilensladic.o7employeeslist.ui.presentation.employee_profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tilensladic.o7employeeslist.R
import com.tilensladic.o7employeeslist.ui.presentation.employee_profile.component.CardItem
import com.tilensladic.o7employeeslist.util.UiEvent
import com.tilensladic.o7employeeslist.util.components.ProfileImage
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
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                ProfileImage(modifier = Modifier.size(150.dp))
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = "${viewModel.employee?.name}", fontSize = 24.sp)
                /* TODO Maybe add Age */
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalArrangement = Arrangement.Center
            ) {
                Card(
                    elevation = 5.dp,
                    shape = RoundedCornerShape(5.dp),
                    backgroundColor = MaterialTheme.colors.secondary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(5.dp))
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            CardItem(
                                icon = R.drawable.birthday_icon,
                                label = "Birthday",
                                content = "${viewModel.employee?.birthday_date}"
                            )
                        }

                    }
                }
            }

        }


    }

}