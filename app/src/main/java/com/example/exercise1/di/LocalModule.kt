package com.example.exercise1.di

import android.content.Context
import com.example.exercise1.data.local.dao.EmployeeDao
import com.example.exercise1.data.local.dao.SalaryDao
import com.example.exercise1.data.local.database.ExerciseDatabase
import com.example.exercise1.data.local.repository.LocalRepository
import com.example.exercise1.data.local.repository.LocalRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocalModule {


    @Singleton
    @Provides
    fun provideExerciseDatabase(@ApplicationContext context: Context) : ExerciseDatabase {
        return ExerciseDatabase.getDatabase(context)
    }


    @Singleton
    @Provides
    fun provideEmployeeDao(database: ExerciseDatabase) : EmployeeDao{
        return database.employeeDao()
    }

    @Singleton
    @Provides
    fun provideSalaryDao(database: ExerciseDatabase) : SalaryDao {
        return database.salaryDao()
    }


    @Singleton
    @Provides
    fun provideLocalRepository(employeeDao: EmployeeDao, salaryDao: SalaryDao) : LocalRepository {
        return LocalRepositoryImpl(employeeDao, salaryDao)
    }




}