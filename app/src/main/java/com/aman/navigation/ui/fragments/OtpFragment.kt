package com.aman.navigation.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.KeyEvent.ACTION_DOWN
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.aman.navigation.R
import com.aman.navigation.databinding.FragmentOtpBinding
import com.aman.navigation.ui.utils.GeneralFunctions

class OtpFragment : Fragment() {

    lateinit var binding: FragmentOtpBinding
    lateinit var initView: View
    lateinit var mobileNumber: String
    lateinit var otp: String
    var from_forget: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_otp, container, false)
        initView = binding.root
        mobileNumber = OtpFragmentArgs.fromBundle(requireArguments())!!.StringMobileNumber
        from_forget = OtpFragmentArgs.fromBundle(requireArguments())!!.StringFromForget

        return initView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMembers()
        setListeners()
    }

    private fun setListeners() {
        binding.etOtp1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (charSequence.length == 1) {
                    binding.etOtp2.requestFocus(View.FOCUS_DOWN);
                }
            }

            override fun afterTextChanged(editable: Editable) {
                if (digitsVerified()) GeneralFunctions().enableButton(true, binding.btnVerifyOtp) else GeneralFunctions().enableButton(true, binding.btnVerifyOtp)
            }
        })

        binding.etOtp2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (charSequence.length > 0) {
                    binding.etOtp3.requestFocus()
                }
            }
            override fun afterTextChanged(editable: Editable) {
                if (editable.length == 0) {
                    binding.etOtp2.requestFocus(View.FOCUS_DOWN)
                }
                if (digitsVerified()) GeneralFunctions().enableButton(true, binding.btnVerifyOtp) else GeneralFunctions().enableButton(true, binding.btnVerifyOtp)
            }
        })

        binding.etOtp3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (charSequence.length > 0) {
                    binding.etOtp4.requestFocus()
                }
            }

            override fun afterTextChanged(editable: Editable) {
                if (editable.length == 0) {
                    binding.etOtp3.requestFocus(View.FOCUS_DOWN)
                }
                if (digitsVerified()) GeneralFunctions().enableButton(true, binding.btnVerifyOtp) else GeneralFunctions().enableButton(true, binding.btnVerifyOtp)
            }
        })

        binding.etOtp4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (charSequence.length > 0) {
                    GeneralFunctions().hideKeyboard(view!!)
                    verifyOtp()
                }
            }

            override fun afterTextChanged(editable: Editable) {
                if (editable.length == 0) {
                    binding.etOtp4.requestFocus(View.FOCUS_DOWN);
                }
                if (digitsVerified()) GeneralFunctions().enableButton(true, binding.btnVerifyOtp) else GeneralFunctions().enableButton(true, binding.btnVerifyOtp)
            }
        })
        binding.etOtp2.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                //Perform Code
                if (binding.etOtp2.text.length == 0) {
                    binding.etOtp1.requestFocus()
                    return@OnKeyListener true
                }
            }
            false
        })

        binding.etOtp3.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                //Perform Code
                if (binding.etOtp3.text.length == 0) {
                    binding.etOtp2.requestFocus()
                    return@OnKeyListener true
                }
            }
            false
        })

        binding.etOtp4.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                //Perform Code
                if (binding.etOtp4.text.length == 0) {
                    binding.etOtp3.requestFocus()
                    return@OnKeyListener true
                }
            }
            false
        })

        binding.btnVerifyOtp.setOnClickListener({
            verifyOtp()
        })
    }

    fun verifyOtp(){
        if(binding.etOtp1.text.isNullOrEmpty()
            && binding.etOtp2.text.isNullOrEmpty()
            && binding.etOtp3.text.isNullOrEmpty()
            && binding.etOtp4.text.isNullOrEmpty()){
            GeneralFunctions().showToast(requireContext(), resources.getString(R.string.enter_otp))
            return
        }else{
            findNavController().navigate(R.id.action_otpFragment_to_newPasswordFragment)

        }
    }

    fun digitsVerified(): Boolean {
        return (binding.etOtp1.length() != 0 && binding.etOtp2.length() != 0 && binding.etOtp3.length() != 0 && binding.etOtp4.length() != 0 )
    }

    private fun setMembers() {
        binding.lblOtp.text = resources.getString(R.string.enter_otp_sent_to) +" "+ mobileNumber
    }

    companion object {
    }
}