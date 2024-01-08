package com.example.exercise1.pages.salary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.exercise1.R
import com.example.exercise1.databinding.FragmentNewSalaryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewSalaryFragment : DialogFragment() {

    private var _binding : FragmentNewSalaryBinding ?= null
    private val binding get() = _binding!!


    private val viewModel: SalaryViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewSalaryBinding.inflate(inflater, container, false)
        val rootView = binding.root

        rootView.post {
            dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, resources.getDimensionPixelSize(R.dimen.dialog_height))
        }
        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddSalary.setOnClickListener {
            println("i click this")
            viewModel.addEmployeeSalary(binding.etAmount.text.toString())
        }

        viewModel.successAddSalary.observe(viewLifecycleOwner){
            if(it){
                println("Success add salary")
                dismiss()
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}