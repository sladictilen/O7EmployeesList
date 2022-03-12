package com.tilensladic.o7employeeslist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [EmployeeData::class],
    version = 1
)
abstract class EmployeesDatabase: RoomDatabase() {
    abstract val dao: EmployeesDao
}