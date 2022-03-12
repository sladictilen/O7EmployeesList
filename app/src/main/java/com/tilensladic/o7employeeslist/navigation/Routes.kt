package com.tilensladic.o7employeeslist.navigation

sealed class Routes(val route: String){
    object EmployeesListScreen : Routes("employees_list")
}
