package com.tilensladic.o7employeeslist.ui.presentation.employees_list

sealed class EmployeesListEvent {
    data class OnEmployeeClick(val id_employee: Int) : EmployeesListEvent()
    object OnAddEmployeeClick : EmployeesListEvent()
}
