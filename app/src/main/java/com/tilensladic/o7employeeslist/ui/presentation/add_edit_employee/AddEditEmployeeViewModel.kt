package com.tilensladic.o7employeeslist.ui.presentation.add_edit_employee

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.tilensladic.o7employeeslist.data.database.EmployeesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditEmployeeViewModel @Inject constructor(
    private val employeesRepository: EmployeesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var name by mutableStateOf("")
        private set
    var salary by mutableStateOf(0.0)
        private set
    var birthday by mutableStateOf("")
        private set
    var gender by mutableStateOf("")
        private set

    fun onEvent(event: AddEditEmployeeEvent) {
        when (event) {
            is AddEditEmployeeEvent.OnNameValueChange -> name = event.name
        }
    }
}