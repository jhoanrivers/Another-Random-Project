package com.example.exercise1.data.local.repository

import com.example.exercise1.data.local.dao.EmployeeDao
import com.example.exercise1.data.local.dao.SalaryDao
import com.example.exercise1.data.local.entity.EmployeeEntity
import com.example.exercise1.data.local.entity.SalaryEntity
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val employeeDao: EmployeeDao, private val salaryDao: SalaryDao) : LocalRepository {


    override suspend fun getAllEmployee(): List<EmployeeEntity> {
        return employeeDao.getAllEmployee()
    }

    override suspend fun createEmployee(employeeEntity: EmployeeEntity) {
        employeeDao.insertEmployee(employeeEntity)
    }

    override suspend fun createSalary(salaryEntity: SalaryEntity) {
        salaryDao.insertSalary(salaryEntity)
    }

    override suspend fun getAllSalaryByEmployeeId(employeeId: Int): List<SalaryEntity> {
        return salaryDao.getSalaryForEmployee(employeeId)
    }


}