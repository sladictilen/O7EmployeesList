package com.tilensladic.o7employeeslist.ui.presentation.add_edit_employee

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
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
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddEditEmployeeViewModel @Inject constructor(
    private val employeesRepository: EmployeesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val calendar = Calendar.getInstance()
    private val idEmployee = savedStateHandle.get<Int>("id_employee")
    private var employee: EmployeeData? = null


    var title by mutableStateOf("Add new employee")
        private set
    var button by mutableStateOf("Add employee")
        private set
    var editing by mutableStateOf(false)    // to know if we are adding or editing
        private set


    var name by mutableStateOf("")
        private set
    var salary by mutableStateOf("")
        private set

    var birthday by mutableStateOf("Select Birthday")
        private set
    var birthdayDay by mutableStateOf(calendar.get(Calendar.DAY_OF_MONTH))
        private set
    var birthdayMonth by mutableStateOf(calendar.get(Calendar.MONTH))
        private set
    var birthdayYear by mutableStateOf(calendar.get(Calendar.YEAR))
        private set

    var gender by mutableStateOf("Male")
        private set
    var errorVisibility by mutableStateOf(false)
        private set

    init {
        if (idEmployee != -1) {
            title = "Edit employee"
            button = "Save changes"
            editing = true
            viewModelScope.launch {
                employee = idEmployee?.let { employeesRepository.getEmployeeById(it) }
                name = employee?.name.toString()
                salary = employee?.salary.toString()
                birthday = employee?.birthday_date.toString()
            }
        }
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: AddEditEmployeeEvent) {
        when (event) {
            is AddEditEmployeeEvent.OnNameValueChange -> name = event.name
            is AddEditEmployeeEvent.OnSalaryValueChange -> salary = event.salary
            is AddEditEmployeeEvent.OnGenderValueChange -> gender = event.gender
            is AddEditEmployeeEvent.OnBirthdayValueChange -> birthday = event.birthday
            is AddEditEmployeeEvent.OnAddEditEmployeeClick -> {
                viewModelScope.launch {
                    if (name.isBlank() || birthday == "Select Birthday" || gender.isBlank() || salary.isBlank()) {
                        errorVisibility = true
                        return@launch
                    }
                    if (!editing) {
                        employeesRepository.addEditEmployee(
                            EmployeeData(
                                name = name,
                                birthday_date = birthday,
                                gender = gender,
                                salary = salary.toDouble()
                            )
                        )
                        sendUiEvent(UiEvent.PopBackStack)
                    } else {
                        employeesRepository.addEditEmployee(
                            EmployeeData(
                                id_employee = idEmployee,
                                name = name,
                                birthday_date = birthday,
                                gender = gender,
                                salary = salary.toDouble()
                            )
                        )
                        sendUiEvent(UiEvent.Navigate(Routes.EmployeeProfileScreen.route + "?id_employee=$idEmployee"))
                    }

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