package com.tilensladic.o7employeeslist.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeesDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEditEmployee(employee: EmployeeData)

    @Delete
    suspend fun deleteEmployee(employee: EmployeeData)

    @Query("SELECT * FROM EmployeeData")
    fun getEmployees(): Flow<List<EmployeeData>>

    @Query("SELECT * FROM EmployeeData WHERE id_employee = :id_employee")
    suspend fun getEmployeeById(id_employee: Int): EmployeeData?
}