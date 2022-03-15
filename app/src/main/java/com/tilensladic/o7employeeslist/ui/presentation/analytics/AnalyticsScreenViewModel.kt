package com.tilensladic.o7employeeslist.ui.presentation.analytics

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tilensladic.o7employeeslist.data.database.EmployeesRepository
import com.tilensladic.o7employeeslist.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnalyticsScreenViewModel @Inject constructor(
    private val employeesRepository: EmployeesRepository
) : ViewModel() {

    private val employees = employeesRepository.getEmployees()

    var employeesCount by mutableStateOf(0)
        private set
    var averageAge by mutableStateOf(0.0)
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

    private suspend fun getNumberOfEmployees() {
        employees.collectIndexed { _, value ->
            employeesCount = value.size
        }
    }

    private suspend fun getAverageAge(){
        employees.collectIndexed { _, value ->
            var summedAge : Int
            for (x in value){
                /* Todo */
            }
        }
    }
    private suspend fun getMedianAge() {

    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}