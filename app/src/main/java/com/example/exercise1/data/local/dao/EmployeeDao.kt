package com.example.exercise1.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exercise1.data.local.entity.EmployeeEntity


@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employee")
    suspend fun getAllEmployee() : List<EmployeeEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employeeEntity: EmployeeEntity)


}
