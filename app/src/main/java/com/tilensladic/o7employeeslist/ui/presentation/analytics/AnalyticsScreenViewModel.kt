package com.tilensladic.o7employeeslist.ui.presentation.analytics

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tilensladic.o7employeeslist.data.database.EmployeesRepository
import com.tilensladic.o7employeeslist.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject
import kotlin.time.measureTime

@HiltViewModel
class AnalyticsScreenViewModel @Inject constructor(
    private val employeesRepository: EmployeesRepository
) : ViewModel() {

    private val employees = employeesRepository.getEmployees()

    var employeesCount by mutableStateOf(0)
        private set
    var averageAge by mutableStateOf(0.0)
        private set

    var maleCount by mutableStateOf(0)
        private set
    var femaleCount by mutableStateOf(0)
        private set

    var medianAge by mutableStateOf(0.0)
        private set
    var highestSalary by mutableStateOf(0.0)
        private set
    var maleVsFemale by mutableStateOf(0.0)
        private set

    init {
        viewModelScope.launch {
            getData()
        }

    }


    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: AnalyticsScreenEvent) {
        when (event) {
            is AnalyticsScreenEvent.OnBackPressed -> {
                sendUiEvent(UiEvent.PopBackStack)
            }
        }
    }

    private suspend fun getData() {
        employees.collectIndexed { _, employeeList ->
            employeesCount = employeeList.size
            var currentHighestSalary = 0.0
            var summedAge = 0
            var allAges = mutableListOf<Int>()
            for (employee in employeeList) {
                val currentAge = getAge(employee.birthday_date)
                summedAge += currentAge
                allAges.add(currentAge)
                if (employee.gender == "Male") {
                    maleCount++
                } else {
                    femaleCount++
                }
                if (employee.salary > currentHighestSalary) {
                    currentHighestSalary = employee.salary
                }
            }
            allAges.sortDescending()
            averageAge = (summedAge / employeesCount).toDouble()
            highestSalary = currentHighestSalary
            maleVsFemale = (maleCount / femaleCount).toDouble()

            if ((allAges.size % 2) != 0) {  // if odd size
                medianAge = allAges[(allAges.size - 2) / 2].toDouble()
            } else { // if even size
                medianAge =
                    ((allAges[allAges.size / 2].toDouble() + allAges[(allAges.size / 2) - 1].toDouble()) / 2)
            }


        }
    }


    private fun getAge(birthday: String): Int {
        val birthdayArr = birthday.split(".").map { it.toInt() }
        val calendar = Calendar.getInstance()

        val birthdayYear = birthdayArr[2]
        val birthdayMonth = birthdayArr[1]
        val birthdayDay = birthdayArr[0]
        val todayDay = calendar.get(Calendar.DAY_OF_MONTH)
        val todayMonth = calendar.get(Calendar.MONTH) + 1
        val todayYear = calendar.get(Calendar.YEAR)

        var age = todayYear - birthdayYear

        return if (birthdayMonth > todayMonth) {
            age--
            age
        } else {
            if (birthdayMonth == todayMonth && todayDay < birthdayDay) {
                age--
            }
            age
        }
    }


    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}