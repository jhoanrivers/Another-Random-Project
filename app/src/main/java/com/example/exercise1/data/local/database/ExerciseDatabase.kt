package com.example.exercise1.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exercise1.data.local.dao.EmployeeDao
import com.example.exercise1.data.local.dao.SalaryDao
import com.example.exercise1.data.local.entity.EmployeeEntity
import com.example.exercise1.data.local.entity.SalaryEntity


@Database(
    entities = [SalaryEntity::class, EmployeeEntity::class],
    version = 2,
    exportSchema = false
)
abstract class ExerciseDatabase : RoomDatabase() {


    abstract fun employeeDao(): EmployeeDao

    abstract fun salaryDao(): SalaryDao


    companion object {

        @Volatile
        private var INSTANCE: ExerciseDatabase? = null

        fun getDatabase(context: Context): ExerciseDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExerciseDatabase::class.java,
                    "exercise_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }


    }


}