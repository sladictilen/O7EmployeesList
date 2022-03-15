package com.tilensladic.o7employeeslist.ui.presentation.employee_profile.helpers

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.tilensladic.o7employeeslist.ui.presentation.employee_profile.EmployeeProfileEvent
import com.tilensladic.o7employeeslist.ui.presentation.employee_profile.EmployeeProfileScreenViewModel

@Composable
fun CustomAlertDialog(
    viewModel: EmployeeProfileScreenViewModel = hiltViewModel()
) {
    if (viewModel.openDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.openDialog = false },
            title = {
                Text("Delete employee?")
            },
            text = {
                Text(text = "Are you sure you want to delete this employee ( ${viewModel.employee?.name} )?")
            },
            confirmButton = {
                Button(onClick = { viewModel.onEvent(EmployeeProfileEvent.OnConfirmDelete) }) {
                    Text(text = "Delete")
                }
            },
            dismissButton = {
                Button(onClick = { viewModel.openDialog = false }) {
                    Text(text = "Cancel")
                }
            }
        )
    }

}