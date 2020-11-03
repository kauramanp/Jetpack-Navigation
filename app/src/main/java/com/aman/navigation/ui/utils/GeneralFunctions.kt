package com.aman.navigation.ui.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class GeneralFunctions{

    lateinit var toast: Toast

    fun showSnack(view: View, msg: String){
        val snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }.show()
    }

    fun hideKeyboard(view: View) {
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showToast(context: Context, text: String){
        if(this::toast.isInitialized)
       { if(toast != null){
            toast.cancel()
        }}
        toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
        toast.show()
    }

    fun enableButton(isEnabled: Boolean, btn: Button){
        if(isEnabled){
            btn.isEnabled = true
        }else
            btn.isEnabled = false
    }


}