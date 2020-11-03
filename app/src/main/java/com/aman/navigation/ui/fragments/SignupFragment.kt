package com.aman.navigation.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.aman.navigation.R
import com.aman.navigation.databinding.FragmentSignupBinding
import com.aman.navigation.ui.activities.DashboardActivity
import com.aman.navigation.ui.utils.GeneralFunctions
import kotlinx.android.synthetic.main.fragment_signup.view.*


class SignupFragment : Fragment() {
    lateinit var initView : View
    lateinit var binding: FragmentSignupBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)
        initView = binding.root

        return initView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        binding.btnSignup.setOnClickListener {
             performSignup()
        }

        binding.etPassword.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                  performSignup()
                true
            } else {
                false
            }
        }
    }

    private fun performSignup(){
        if(binding.etName.text.toString().isNullOrEmpty()){
            GeneralFunctions().showToast(requireContext(), resources.getString(R.string.enter_name))
            return
        }else if(binding.etEmail.text.toString().isNullOrEmpty()){
            GeneralFunctions().showToast(requireContext(), resources.getString(R.string.enter_email))
            return
        }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text).matches()){
            GeneralFunctions().showToast(requireContext(), resources.getString(R.string.enter_valid_email_id))
            return
        }else if(binding.etMobileNumber.text.toString().isNullOrEmpty()){
            GeneralFunctions().showToast(requireContext(), resources.getString(R.string.enter_registered_mobile_number))
            return
        }else if(binding.etMobileNumber.text.toString().length < 10){
            GeneralFunctions().showToast(requireContext(), resources.getString(R.string.enter_valid_mobile_number))
            return
        }else if(binding.etPassword.text.toString().isNullOrEmpty()){
            GeneralFunctions().showToast(requireContext(), resources.getString(R.string.enter_password))
            return
        }else if(binding.etPassword.text.toString().length < 6){
            GeneralFunctions().showToast(requireContext(), resources.getString(R.string.password_less_than_6))
            return
        }else{
            GeneralFunctions().hideKeyboard(initView)
            val intent = Intent(activity, DashboardActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

}

