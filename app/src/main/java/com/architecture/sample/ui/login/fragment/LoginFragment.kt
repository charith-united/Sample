package com.architecture.sample.ui.login.fragment

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.architecture.sample.R
import com.architecture.sample.databinding.FragmentLoginBinding
import com.architecture.sample.utils.AppConstants
import com.architecture.sample.utils.AppUtils
import com.architecture.sample.utils.CustomTextWatcher

class LoginFragment : Fragment() {

    /** ------------ Instance Variable(s) -------------*/
    private var binding: FragmentLoginBinding? = null
    
    private val clickListener = View.OnClickListener {
        when(areFieldsValid()){
            true -> Toast.makeText(context, "Credentials valid", Toast.LENGTH_SHORT).show()
            false -> Toast.makeText(context, "Please enter valid credentials", Toast.LENGTH_SHORT).show()
        }
    }
    
    private val clickableSpan = object : ClickableSpan(){
        override fun onClick(p0: View) {
            Toast.makeText(context, "Clicked Register", Toast.LENGTH_SHORT).show()
        }
    }
    private val keyTextWatcher by lazy { CustomTextWatcher(binding?.tilLoginKey, getString(R.string.login_id_error), AppConstants.EMAIL_REGEX_VALUE) }
    private val passwordTextWatcher by lazy { CustomTextWatcher(binding?.tilLoginPassword, getString(R.string.login_password_error), AppConstants.PASSWORD_REGEX_VALUE) }

    /** ------------ Overridden Function(s) -------------*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding?.clickListener = clickListener
        binding?.lifecycleOwner = this
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.tvLoginRegister?.apply {
            movementMethod = LinkMovementMethod.getInstance()
            text = AppUtils.generateSpannableString(getString(R.string.login_register), getString(R.string.login_register_link), clickableSpan)
        }
        binding?.tetLoginKey?.addTextChangedListener(keyTextWatcher)
        binding?.tetLoginPassword?.addTextChangedListener(passwordTextWatcher)
    }

    /** ------------ Private Function(s) -------------*/
    
    private fun getKey() = binding?.tetLoginKey?.text ?: ""
    private fun getPassword() = binding?.tetLoginKey?.text ?: ""
    
    private fun isKeyValid() = AppConstants.EMAIL_REGEX_VALUE.toRegex().matches(getKey())
    private fun isPasswordValid() = AppConstants.PASSWORD_REGEX_VALUE.toRegex().matches(getPassword())
    private fun areFieldsValid() = isKeyValid() && isPasswordValid()
}