package com.tilensladic.o7employeeslist.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EmployeeData(
    @PrimaryKey(autoGenerate = true) val id_employee: Int,
    val name: String,
    val birthday_date: String,
    val gender: String,
    val salary: Double
)
