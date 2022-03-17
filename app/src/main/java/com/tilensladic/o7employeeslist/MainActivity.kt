package com.tilensladic.o7employeeslist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tilensladic.o7employeeslist.navigation.Navigation
import com.tilensladic.o7employeeslist.ui.theme.O7EmployeesListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            O7EmployeesListTheme {
                Navigation()
            }
        }
    }
}
