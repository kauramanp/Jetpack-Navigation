package com.aman.navigation.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.aman.navigation.R
import com.aman.navigation.databinding.FragmentLoginBinding
import com.aman.navigation.databinding.FragmentNewPasswordBinding
import com.aman.navigation.ui.activities.DashboardActivity
import com.aman.navigation.ui.utils.GeneralFunctions

class NewPasswordFragment : Fragment() {
    lateinit var initView : View
    lateinit var binding: FragmentNewPasswordBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_password, container, false)
        initView = binding.root

        return initView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        binding.btnSetPassword.setOnClickListener{
            setPassword()
        }

        binding.etConfirmPassword.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ) {
                setPassword()
                true
            } else {
                false
            }
        }
    }

    private fun setPassword() {
         if(binding.etPassword.text.toString().isNullOrEmpty()){
            GeneralFunctions().showToast(requireContext(), resources.getString(R.string.enter_password))
            return
        }else if(binding.etPassword.text.toString().length < 6){
            GeneralFunctions().showToast(requireContext(), resources.getString(R.string.password_less_than_6))
            return
        }else if(binding.etConfirmPassword.text.toString().isNullOrEmpty()){
            GeneralFunctions().showToast(requireContext(), resources.getString(R.string.enter_password))
            return
        }else if(binding.etConfirmPassword.text.toString().length < 6){
            GeneralFunctions().showToast(requireContext(), resources.getString(R.string.password_less_than_6))
            return
        }else if(!binding.etPassword.text.toString().equals(binding.etConfirmPassword.text.toString())){
             GeneralFunctions().showToast(requireContext(), resources.getString(R.string.passwords_not_same))
             return
         }else{
            findNavController().navigate(R.id.action_newPasswordFragment_to_loginFragment)
        }
    }

    companion object {
        private val TAG = LoginFragment::class.java.canonicalName
    }
}