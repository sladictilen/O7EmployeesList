package com.tilensladic.o7employeeslist.ui.presentation.employees_list

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tilensladic.o7employeeslist.R
import com.tilensladic.o7employeeslist.ui.presentation.employees_list.components.EmployeeListItem
import com.tilensladic.o7employeeslist.util.UiEvent

@Composable
fun EmployeesListScreen(
    viewModel: EmployeesListScreenViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    val employees = viewModel.employees.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Employees List") },
                elevation = 5.dp,
                actions = {
                    IconButton(onClick = { viewModel.onEvent(EmployeesListEvent.OnAnalyticsClick) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.analytics_icon),
                            contentDescription = "Analytics"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.add_icon),
                        contentDescription = "Add Employee"
                    )
                },
                text = { Text(text = "Add Employee") },
                onClick = { viewModel.onEvent(EmployeesListEvent.OnAddEmployeeClick) }
            )
        }
    ) {
        // Body
        val scrollState = rememberLazyListState()
        LazyColumn(
            Modifier
                .fillMaxSize()
                .scrollable(state = scrollState, orientation = Orientation.Vertical),
        ) {

            items(employees.value) { employee ->
                EmployeeListItem(
                    employee = employee,
                    modifier = Modifier.clickable {
                        viewModel.onEvent(EmployeesListEvent.OnEmployeeClick(employee.id_employee!!))
                    })
            }
        }

    }
}