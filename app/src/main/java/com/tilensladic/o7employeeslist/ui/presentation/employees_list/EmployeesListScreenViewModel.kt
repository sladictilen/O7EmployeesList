package com.tilensladic.o7employeeslist.ui.presentation.employees_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tilensladic.o7employeeslist.data.database.EmployeeData
import com.tilensladic.o7employeeslist.data.database.EmployeesRepository
import com.tilensladic.o7employeeslist.navigation.Routes
import com.tilensladic.o7employeeslist.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeesListScreenViewModel @Inject constructor(
    private val employeesRepository: EmployeesRepository
) : ViewModel() {
    val employees = employeesRepository.getEmployees()


    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: EmployeesListEvent){
        when(event){
            is EmployeesListEvent.OnAddEmployeeClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.AddEditEmployeeScreen.route))
            }
            is EmployeesListEvent.OnEmployeeClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.EmployeeProfileScreen.route+"?id_employee=${event.id_employee}"))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}