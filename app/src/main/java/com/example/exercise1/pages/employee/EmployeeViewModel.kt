package com.example.exercise1.pages.employee

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise1.data.local.entity.EmployeeEntity
import com.example.exercise1.data.local.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EmployeeViewModel @Inject constructor(private val localRepository: LocalRepository) : ViewModel(){

    val listEmployee = MutableLiveData<List<EmployeeEntity>>()
    val loadingData = MutableLiveData<Boolean>()
    val errorLoadData = MutableLiveData<String>()
    val successCreate = MutableLiveData<Boolean>()

    var employeeName = ""
    var employeeMail = ""


    fun getAllEmployee() {
        viewModelScope.launch {
            try {
                val response = localRepository.getAllEmployee()
                listEmployee.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
                errorLoadData.postValue(e.message)
            }

        }

    }

    fun createNewEmployee() {

        val employee = EmployeeEntity(name = employeeName, email = employeeMail, gender = "Whatever")

        viewModelScope.launch {
            try {
                localRepository.createEmployee(employee)
                successCreate.postValue(true)
            } catch (e: Exception) {
                e.printStackTrace()
                errorLoadData.postValue(e.message)
            }
        }
    }



    fun onChangeEmployeeName(text: String) {
        employeeName = text
    }

    fun onChangeEmployeeEmail(email: String) {
        employeeMail = email
    }

}