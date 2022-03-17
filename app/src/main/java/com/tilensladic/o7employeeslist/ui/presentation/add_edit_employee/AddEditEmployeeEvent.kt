package com.tilensladic.o7employeeslist.ui.presentation.add_edit_employee

sealed class AddEditEmployeeEvent {
    data class OnNameValueChange(val name: String) : AddEditEmployeeEvent()
    data class OnBirthdayValueChange(val birthday: String) : AddEditEmployeeEvent()
    data class OnSalaryValueChange(val salary: String) : AddEditEmployeeEvent()
    data class OnGenderValueChange(val gender: String) : AddEditEmployeeEvent()
    object OnAddEditEmployeeClick : AddEditEmployeeEvent()
    object OnBackOrCancelClick : AddEditEmployeeEvent()
}
