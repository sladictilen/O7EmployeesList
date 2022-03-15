package com.tilensladic.o7employeeslist.ui.presentation.employee_profile

sealed class EmployeeProfileEvent {
    object OnBackPressed: EmployeeProfileEvent()
    object OnDeleteEmployeeClick: EmployeeProfileEvent()
    object OnConfirmDelete: EmployeeProfileEvent()
}