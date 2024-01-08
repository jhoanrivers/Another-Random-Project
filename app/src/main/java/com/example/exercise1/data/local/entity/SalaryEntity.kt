package com.example.exercise1.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(

    foreignKeys = [
        ForeignKey(
            entity = EmployeeEntity::class,
            parentColumns = ["employeeId"],
            childColumns = ["employeeId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    tableName = "salary"


)
data class SalaryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val employeeId: Int,

    val amount: Long,

    val date: Long,



)