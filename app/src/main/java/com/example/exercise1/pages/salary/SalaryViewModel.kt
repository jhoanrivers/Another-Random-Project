package com.example.exercise1.pages.salary

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise1.data.local.entity.EmployeeEntity
import com.example.exercise1.data.local.entity.SalaryEntity
import com.example.exercise1.data.local.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class SalaryViewModel @Inject constructor(private val localRepository: LocalRepository) : ViewModel() {


    val listSalary = MutableLiveData<List<SalaryEntity>>()
    val loadingData = MutableLiveData<Boolean>()
    val errorData = MutableLiveData<String>()
    val successAddSalary = MutableLiveData<Boolean>()
    val employeeData = MutableLiveData<EmployeeEntity>()


    lateinit var employee: EmployeeEntity


    fun onViewLoaded(employeeEntity: EmployeeEntity) {
        this.employee = employeeEntity
        println("emailnya ${this.employee.email}")
        employeeData.postValue(this.employee)
    }


    fun getAllSalary() {
        loadingData.postValue(true)
        viewModelScope.launch {
            try {
                val response = localRepository.getAllSalaryByEmployeeId(employee.employeeId)
                loadingData.postValue(false)
                listSalary.postValue(response)

            } catch (e: Exception) {
                loadingData.postValue(false)
                e.printStackTrace()
                errorData.postValue(e.message)

            }
        }
    }


    fun addEmployeeSalary(amount: String) {
        viewModelScope.launch {
            try {
                val salaryEntity = SalaryEntity(employeeId = employee.employeeId, amount = amount.toLong(), date = System.currentTimeMillis())
                localRepository.createSalary(salaryEntity)
                successAddSalary.postValue(true)
            }catch (e: Exception) {
                e.printStackTrace()
                errorData.postValue(e.message)
            }
        }
    }





}