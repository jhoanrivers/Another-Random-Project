package com.example.exercise1.data.local.repository

import com.example.exercise1.data.local.entity.EmployeeEntity
import com.example.exercise1.data.local.entity.SalaryEntity


interface LocalRepository {


    suspend fun getAllEmployee() : List<EmployeeEntity>

    suspend fun createEmployee(employeeEntity: EmployeeEntity)

    suspend fun createSalary(salaryEntity: SalaryEntity)

    suspend fun getAllSalaryByEmployeeId(employeeId: Int) : List<SalaryEntity>


}