package com.example.exercise1.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee")
data class EmployeeEntity (

    @PrimaryKey(autoGenerate = true)
    val employeeId: Int = 0,

    val name: String,

    val email: String,

    val gender: String


)