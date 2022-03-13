package com.tilensladic.o7employeeslist.navigation

sealed class Routes(val route: String){
    object EmployeesListScreen : Routes("employees_list_screen")
    object AddEditEmployeeScreen : Routes("add_edit_employee_screen")
    object EmployeeProfileScreen : Routes("employee_profile_screen")
}
