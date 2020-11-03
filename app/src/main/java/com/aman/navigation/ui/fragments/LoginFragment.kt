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
import com.aman.navigation.databinding.FragmentLoginBinding
import com.aman.navigation.ui.activities.DashboardActivity
import com.aman.navigation.ui.utils.GeneralFunctions

class LoginFragment : Fragment() {
    lateinit var initView : View
    lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        initView = binding.root

        return initView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        binding.btnLogin.setOnClickListener{
            performLogin()
        }


        binding.tvForgetPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgetPasswordFragment)
        }

        binding.tvSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)

        }

        binding.etPassword.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ) {
                performLogin()
                true
            } else {
                false
            }
        }
    }

    private fun performLogin() {
        if(binding.etEmail.text.toString().isNullOrEmpty()){
            GeneralFunctions().showToast(requireContext(), resources.getString(R.string.enter_email))
            return
        }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text).matches()){
            GeneralFunctions().showToast(requireContext(), resources.getString(R.string.enter_valid_email_id))
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

    companion object {
        private val TAG = LoginFragment::class.java.canonicalName
    }
}