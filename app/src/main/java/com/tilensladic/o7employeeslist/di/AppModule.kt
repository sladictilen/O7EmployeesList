package com.tilensladic.o7employeeslist.di

import android.app.Application
import androidx.room.Room
import com.tilensladic.o7employeeslist.data.database.EmployeesDatabase
import com.tilensladic.o7employeeslist.data.database.EmployeesRepository
import com.tilensladic.o7employeeslist.data.database.EmployeesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideEmployeesDatabase(app: Application): EmployeesDatabase{
        return Room.databaseBuilder(
            app,
            EmployeesDatabase::class.java,
            "employees_db"
        ).fallbackToDestructiveMigration().build()
    }
    @Provides
    @Singleton
    fun provideEmployeesRepository(db: EmployeesDatabase): EmployeesRepository{
        return EmployeesRepositoryImpl(dao = db.dao)
    }
}