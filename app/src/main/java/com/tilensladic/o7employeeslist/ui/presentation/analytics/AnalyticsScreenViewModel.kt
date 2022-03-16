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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
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
    var summedAge by mutableStateOf(0)
        private set
    var medianAge by mutableStateOf(0)
        private set
    var highestSalary by mutableStateOf(0.0)
        private set
    var maleVsFemale by mutableStateOf(0.0)
        private set

    init {
        viewModelScope.launch {
            getNumberOfEmployees()
        }
        viewModelScope.launch {
            getSummedAge()
        }
        viewModelScope.launch {
            getAverageAge()
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
    private suspend fun getData(){
        /*TODO add all analytics in this one*/
    }
    private suspend fun getNumberOfEmployees() {
        employees.collectIndexed { _, value ->
            employeesCount = value.size
        }
    }

    private suspend fun getSummedAge() {
        employees.collectIndexed { _, value ->
            for (employee in value) {

                summedAge += getAge(employee.birthday_date)
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
        } else{
            if(birthdayMonth == todayMonth && todayDay < birthdayDay){
                age--
            }
            age
        }
    }

    private suspend fun getAverageAge(): Double {
        return (summedAge / employeesCount).toDouble()
    }

    private fun getMedianAge() {

    }


    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}