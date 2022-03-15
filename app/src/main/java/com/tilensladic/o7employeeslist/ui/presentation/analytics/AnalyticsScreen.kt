package com.tilensladic.o7employeeslist.ui.presentation.analytics

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.tilensladic.o7employeeslist.R
import com.tilensladic.o7employeeslist.util.UiEvent

@Composable
fun AnalyticsScreen(
    onPopBackStack: () -> Unit,
    viewModel: AnalyticsScreenViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                else -> Unit
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Employee's Analytics")
                },
                navigationIcon = {
                    IconButton(onClick = { viewModel.onEvent(AnalyticsScreenEvent.OnBackPressed) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back_icon),
                            contentDescription = "back"
                        )
                    }
                }
            )
        }
    ) {
        Text(text = "Employees: ${viewModel.employeesCount}")
    }
}