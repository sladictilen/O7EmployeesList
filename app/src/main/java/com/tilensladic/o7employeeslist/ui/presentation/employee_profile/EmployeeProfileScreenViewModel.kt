package com.tilensladic.o7employeeslist.ui.presentation.employee_profile

import android.util.Log
import android.util.Log.ERROR
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tilensladic.o7employeeslist.data.database.EmployeeData
import com.tilensladic.o7employeeslist.data.database.EmployeesRepository
import com.tilensladic.o7employeeslist.ui.presentation.employee_profile.helpers.GoogleData
import com.tilensladic.o7employeeslist.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject

@HiltViewModel
class EmployeeProfileScreenViewModel @Inject constructor(
    private val employeesRepository: EmployeesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var employee by mutableStateOf<EmployeeData?>(null)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    // For web scraping, show loading
    var loading by mutableStateOf(true)
        private set
    var result by mutableStateOf(listOf<GoogleData>())
        private set
    var error by mutableStateOf("")
        private set

    var openDialog by mutableStateOf(false)

    init {
        val idEmployee = savedStateHandle.get<Int>("id_employee")!!
        if (idEmployee != -1) {
            viewModelScope.launch {
                employee = employeesRepository.getEmployeeById(idEmployee)
                googleScrape("${employee?.name?.replace(" ", "+")}")
            }
        }


    }

    fun onEvent(event: EmployeeProfileEvent) {
        when (event) {
            is EmployeeProfileEvent.OnBackPressed -> {
                sendUiEvent(UiEvent.PopBackStack)
            }
            is EmployeeProfileEvent.OnDeleteEmployeeClick -> {
                openDialog = true
            }
            is EmployeeProfileEvent.OnConfirmDelete -> {
                openDialog = false
                viewModelScope.launch {
                    employee?.let { employeesRepository.deleteEmployee(it) }
                }
                sendUiEvent(UiEvent.PopBackStack)
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    private suspend fun googleScrape(searchQuery: String) {
        try {
            val data: Document = withContext(Dispatchers.IO) {
                Jsoup.connect("https://www.google.si/search?q=$searchQuery").get()
            }

            val headers: Elements = data.select("h3")
            val urls: Elements = data.select("#search a") // Search only in div id = Search
            var index = 0
            for (element in headers) {
                if (index < 5) {
                    var index2 = 0
                    for (element2 in urls) {
                        if (index2 == index) {
                            result = result + GoogleData(
                                element.text(),
                                element2.attr("href") // get href value)
                            )
                        }
                        index2 += 1
                    }
                } else {
                    break
                }
                index += 1

            }
            loading = false
        } catch (e: Exception) {
            //result = "Error fetching data from Google"
            loading = false
        }


    }

}