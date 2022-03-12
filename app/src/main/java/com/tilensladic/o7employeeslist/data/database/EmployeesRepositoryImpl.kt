package com.tilensladic.o7employeeslist.data.database

import kotlinx.coroutines.flow.Flow

class EmployeesRepositoryImpl(
    private val dao: EmployeesDao
): EmployeesRepository {
    override suspend fun addEditEmployee(employee: EmployeeData) {
        dao.addEditEmployee(employee)
    }

    override suspend fun deleteEmployee(employee: EmployeeData) {
        dao.deleteEmployee(employee)
    }

    override fun getEmployees(): Flow<List<EmployeeData>> {
        return dao.getEmployees()
    }

    override fun getEmployeeById(id_employee: Int): EmployeeData? {
        return dao.getEmployeeById(id_employee)
    }
}