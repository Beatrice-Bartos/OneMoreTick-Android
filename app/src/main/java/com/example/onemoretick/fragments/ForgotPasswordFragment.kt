package com.example.onemoretick.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.onemoretick.R
import com.example.onemoretick.helpers.UtilValidators
import com.example.onemoretick.interfaces.ActivitiesFragmentsCommunication
import com.example.onemoretick.models.request.CreateNewPasswordRequest
import com.example.onemoretick.viewModel.ChangePasswordViewModel

class ForgotPasswordFragment : Fragment() {
    private var fragmentsCommunication: ActivitiesFragmentsCommunication? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val changePasswordViewModel by viewModels<ChangePasswordViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recovery_pass, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ActivitiesFragmentsCommunication) {
            fragmentsCommunication = context as ActivitiesFragmentsCommunication
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.resetPassBtn).setOnClickListener { validateEmail() }
    }

    private fun goToLoginFragment() {
        fragmentsCommunication?.onReplaceFragment(LoginFragment.TAG_LOGIN)
    }

    private fun validateEmail() {
        if (view == null) {
            return
        }
        val emailEdtText = requireView().findViewById<EditText>(R.id.email_text_input_editText)
        val email = emailEdtText.text.toString().trim { it <= ' ' }
        val passwordEditText =
            requireView().findViewById<EditText>(R.id.password_text_input_editText)
        val password = passwordEditText.text.toString().trim { it <= ' ' }

        if (!UtilValidators.isValidEmail(email)) {
            emailEdtText.error = "Invalid Email"
            return
        } else {
            emailEdtText.error = null
        }
        if (!UtilValidators.isValidPassword(password)) {
            passwordEditText.error = "Invalid Password"
            return
        } else {
            passwordEditText.error = null
        }
        resetPassword(email, password)
    }

    private fun changePassword(email: String, password: String): Boolean {
        val createNewPasswordRequest = CreateNewPasswordRequest(email, password)
        changePasswordViewModel.changePassword(createNewPasswordRequest)
        return true
    }

    private fun resetPassword(email: String, password: String) {
        changePassword(email, password)
        Toast.makeText(
            context,
            "The email was sent successfully!",
            Toast.LENGTH_SHORT
        ).show()
        goToLoginFragment()
    }

    companion object {
        const val TAG_FORGOT_PASS = "TAG_FORGOT_PASS"
        fun newInstance(): ForgotPasswordFragment {
            val args = Bundle()
            val fragment = ForgotPasswordFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
