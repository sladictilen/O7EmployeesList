package com.tilensladic.o7employeeslist.ui.presentation.add_edit_employee

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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditEmployeeViewModel @Inject constructor(
    private val employeesRepository: EmployeesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var name by mutableStateOf("")
        private set
    var salary by mutableStateOf("")
        private set
    var birthday by mutableStateOf("")
        private set
    var gender by mutableStateOf("")
        private set
    var errorVisibilityName by mutableStateOf(false)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: AddEditEmployeeEvent) {
        when (event) {
            is AddEditEmployeeEvent.OnNameValueChange -> name = event.name
            is AddEditEmployeeEvent.OnSalaryValueChange -> salary = event.salary
            is AddEditEmployeeEvent.OnGenderValueChange -> gender = event.gender
            is AddEditEmployeeEvent.OnBirthdayValueChange -> birthday = event.birthday
            is AddEditEmployeeEvent.OnAddEmployeeClick -> {
                viewModelScope.launch {
                    if (name.isBlank() || birthday.isBlank() || gender.isBlank() || salary.isBlank()) {
                        errorVisibilityName = true
                        return@launch
                    }
                    employeesRepository.addEditEmployee(
                        EmployeeData(
                            name = name,
                            birthday_date = birthday,
                            gender = gender,
                            salary = salary.toDouble()
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
                }

            }
            is AddEditEmployeeEvent.OnBackOrCancelClick -> {
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