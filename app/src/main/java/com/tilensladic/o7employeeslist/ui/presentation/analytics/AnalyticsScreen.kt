package com.tilensladic.o7employeeslist.ui.presentation.analytics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tilensladic.o7employeeslist.R
import com.tilensladic.o7employeeslist.ui.presentation.analytics.components.AnalyticsItem
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
                    Text(text = "Employees Analytics")
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
        Row(modifier = Modifier.padding(10.dp)) {
            Card(
                elevation = 5.dp,
                backgroundColor = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(10.dp)
            ) {
                Column() {
                    AnalyticsItem(
                        label = "Number of employees",
                        content = "${viewModel.employeesCount}"
                    )
                    AnalyticsItem(
                        label = "Male employees",
                        content = "${viewModel.maleCount}"
                    )
                    AnalyticsItem(
                        label = "Female employees",
                        content = "${viewModel.femaleCount}"
                    )
                    AnalyticsItem(label = "Highest salary", content = "${viewModel.highestSalary}€")
                    AnalyticsItem(label = "Average age", content = "${viewModel.averageAge}")
                    AnalyticsItem(label = "Median age", content = "${viewModel.medianAge}")
                    AnalyticsItem(
                        label = "Male vs. Female ratio simplified",
                        content = viewModel.maleVsFemale
                    )
                }
            }

        }

    }
}