package com.tilensladic.o7employeeslist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tilensladic.o7employeeslist.ui.presentation.employees_list.EmployeesListScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.EmployeesListScreen.route){
        composable(route = Routes.EmployeesListScreen.route){
            EmployeesListScreen()
        }
    }

}