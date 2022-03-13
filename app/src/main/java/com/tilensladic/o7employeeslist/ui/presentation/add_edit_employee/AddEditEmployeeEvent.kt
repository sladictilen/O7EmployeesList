package com.tilensladic.o7employeeslist.ui.presentation.add_edit_employee

sealed class AddEditEmployeeEvent{
    data class OnNameValueChange(val name: String): AddEditEmployeeEvent()
}
