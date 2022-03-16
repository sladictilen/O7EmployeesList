package com.tilensladic.o7employeeslist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tilensladic.o7employeeslist.ui.presentation.add_edit_employee.AddEditEmployeeScreen
import com.tilensladic.o7employeeslist.ui.presentation.analytics.AnalyticsScreen
import com.tilensladic.o7employeeslist.ui.presentation.employee_profile.EmployeeProfileScreen
import com.tilensladic.o7employeeslist.ui.presentation.employees_list.EmployeesListScreen
import com.tilensladic.o7employeeslist.util.UiEvent

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.EmployeesListScreen.route) {

        composable(route = Routes.EmployeesListScreen.route) {
            EmployeesListScreen(onNavigate = { navController.navigate(it.route) })
        }

        composable(
            route = Routes.AddEditEmployeeScreen.route + "?id_employee={id_employee}",
            arguments = listOf(
                navArgument(name = "id_employee") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            ),
        ) {
            AddEditEmployeeScreen(onPopBackStack = { navController.popBackStack() })
        }

        composable(
            route = Routes.EmployeeProfileScreen.route + "?id_employee={id_employee}",
            arguments = listOf(
                navArgument(name = "id_employee") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )) {
            EmployeeProfileScreen(onPopBackStack = { navController.popBackStack() })
        }

        composable(route = Routes.AnalyticsScreen.route) {
            AnalyticsScreen(onPopBackStack = { navController.popBackStack() })
        }

    }

}