package com.example.exercise1.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exercise1.data.local.entity.SalaryEntity


@Dao
interface SalaryDao {


    @Query("SELECT * FROM salary WHERE employeeId = :employeeId")
    suspend fun getSalaryForEmployee(employeeId: Int) : List<SalaryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSalary(salaryEntity: SalaryEntity)
}