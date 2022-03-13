package com.tilensladic.o7employeeslist.ui.presentation.employee_profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tilensladic.o7employeeslist.data.database.EmployeeData
import com.tilensladic.o7employeeslist.data.database.EmployeesRepository
import com.tilensladic.o7employeeslist.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeProfileScreenViewModel @Inject constructor(
    private val employeesRepository: EmployeesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var employee by mutableStateOf<EmployeeData?>(null)
        private set


    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val idEmployee = savedStateHandle.get<Int>("id_employee")!!
        if (idEmployee != -1) {
            viewModelScope.launch {
                employee = employeesRepository.getEmployeeById(idEmployee)
            }
        }
    }

    fun onEvent(event: EmployeeProfileEvent){
        when(event){
            is EmployeeProfileEvent.OnBackPressed -> {
                sendUiEvent(UiEvent.PopBackStack)
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}