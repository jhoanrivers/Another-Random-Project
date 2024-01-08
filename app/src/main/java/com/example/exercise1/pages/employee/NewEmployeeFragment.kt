package com.example.exercise1.pages.employee

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.exercise1.R
import com.example.exercise1.databinding.FragmentNewEmployeeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewEmployeeFragment : Fragment() {

    private val employeeViewModel: EmployeeViewModel by activityViewModels()
    private var _binding: FragmentNewEmployeeBinding ? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewEmployeeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        bindFields()
        bindViewModel()
        bindViewEvents()



    }

    fun initView() {

    }

    fun bindFields() {
        binding.etEmployeeName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                employeeViewModel.onChangeEmployeeName(binding.etEmployeeName.text.toString())
            }

        })

        binding.etEmployeeEmail.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                employeeViewModel.onChangeEmployeeEmail(binding.etEmployeeEmail.text.toString())
            }

        })



    }

    fun bindViewModel() {

        employeeViewModel.successCreate.observe(viewLifecycleOwner){
            if(it){
                requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            }
        }

    }

    fun bindViewEvents() {

        binding.btnAdd.setOnClickListener {
            employeeViewModel.createNewEmployee()
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }





}