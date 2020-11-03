package com.aman.navigation.ui.fragments

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.aman.navigation.R
import com.aman.navigation.databinding.FragmentForgetPasswordBinding
import com.aman.navigation.ui.utils.GeneralFunctions
import kotlinx.android.synthetic.main.fragment_forget_password.*


class ForgetPasswordFragment : Fragment() {

    lateinit var initView: View
    lateinit var binding: FragmentForgetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forget_password, container, false)

        initView = binding.root

        return initView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
    }

    private fun setListeners() {
        binding.tvLogin.setOnClickListener{
            findNavController().popBackStack()
        }
        binding.btnOtp.setOnClickListener {
            proceedToOTP();
        }
        binding.etMobileNumber.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ) {
                proceedToOTP()
                true
            } else {
                false
            }
        }
    }

    fun proceedToOTP(){
        if(binding.etMobileNumber.text.toString().isNullOrEmpty()){
            GeneralFunctions().showToast(requireContext(), resources.getString(R.string.enter_registered_mobile_number))
            return
        }else if(binding.etMobileNumber.text.toString().length < 10){
            GeneralFunctions().showToast(requireContext(), resources.getString(R.string.enter_valid_mobile_number))
            return
        }else{
            GeneralFunctions().hideKeyboard(initView)
            val bundle = Bundle()
            bundle.putString(resources.getString(R.string.mobile_number), binding.etMobileNumber.text!!.toString())
            bundle.putBoolean(resources.getString(R.string.from_forget), true)
            findNavController().navigate(R.id.action_forgetPasswordFragment_to_otpFragment, bundle)
        }
    }

    companion object {
        private val TAG = ForgetPasswordFragment::class.java.canonicalName
    }
}