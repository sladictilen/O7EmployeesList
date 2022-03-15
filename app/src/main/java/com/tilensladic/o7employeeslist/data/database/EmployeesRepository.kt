package com.tilensladic.o7employeeslist.data.database

import kotlinx.coroutines.flow.Flow

interface EmployeesRepository {
    suspend fun addEditEmployee(employee: EmployeeData)

    suspend fun deleteEmployee(employee: EmployeeData)

    fun getEmployees(): Flow<List<EmployeeData>>

    suspend fun getEmployeeById(id_employee: Int): EmployeeData?
}