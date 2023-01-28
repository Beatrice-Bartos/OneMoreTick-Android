package com.example.onemoretick.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.onemoretick.R
import com.example.onemoretick.helpers.UtilValidators
import com.example.onemoretick.interfaces.ActivitiesFragmentsCommunication
import com.example.onemoretick.models.request.LoginUserRequest
import com.example.onemoretick.viewModel.LoginViewModel
import androidx.fragment.app.viewModels
import com.example.onemoretick.MainActivity
import com.example.onemoretick.activities.HomeActivity

class LoginFragment : Fragment() {
    private var fragmentsCommunication: ActivitiesFragmentsCommunication? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ActivitiesFragmentsCommunication) {
            fragmentsCommunication = context as ActivitiesFragmentsCommunication
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editTextEmail = view.findViewById<EditText>(R.id.email_text_input_editText)
        val editTextPassword = view.findViewById<EditText>(R.id.password_text_input_editText)
        val rememberMe = view.findViewById<CheckBox>(R.id.remember_me_checkBox)
        val sharedPreferences =
            requireContext().getSharedPreferences("mainPrefs", Context.MODE_PRIVATE)
        if (sharedPreferences.contains("Email") && sharedPreferences.contains("Password")) {
            rememberMe.isChecked = true
            val emailStr = sharedPreferences.getString("Email", "")
            val passwordStr = sharedPreferences.getString("Password", "")
            editTextEmail.setText(emailStr)
            editTextPassword.setText(passwordStr)
        }
        view.findViewById<View>(R.id.login_button).setOnClickListener { validateEmailAndPassword() }
        view.findViewById<View>(R.id.forgotPassword_textView)
            .setOnClickListener { goToForgotPasswordFragment() }
        view.findViewById<View>(R.id.register_textView)
            .setOnClickListener { goToRegisterFragment() }
        rememberMe.setOnCheckedChangeListener { compoundButton, _ ->
            if (compoundButton.isChecked) {
                save("Email", editTextEmail)
                save("Password", editTextPassword)
            } else if (!compoundButton.isChecked) {
                delete()
                delete()
            }
        }
    }

    private fun goToRegisterFragment() {
        Toast.makeText(context, "Go to Register Fragment", Toast.LENGTH_SHORT).show();
        fragmentsCommunication?.onReplaceFragment(RegisterFragment.TAG_REGISTER)
    }

    private fun goToForgotPasswordFragment() {
        Toast.makeText(context, "Go to Forgot Password Fragment", Toast.LENGTH_SHORT).show();
        fragmentsCommunication?.onReplaceFragment(ForgotPasswordFragment.TAG_FORGOT_PASS)
    }

    private fun goToHomeActivity() {
        Toast.makeText(context, "Login success!", Toast.LENGTH_SHORT).show();
        val intent = Intent(activity, HomeActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun validateEmailAndPassword() {
        if (view == null) {
            return
        }
        val emailEdtText = requireView().findViewById<EditText>(R.id.email_text_input_editText)
        val passwordEdtText =
            requireView().findViewById<EditText>(R.id.password_text_input_editText)
        val email = emailEdtText.text.toString().trim { it <= ' ' }
        val password = passwordEdtText.text.toString()
        if (!UtilValidators.isValidEmail(email)) {
            emailEdtText.error = "Invalid Email"
            return
        } else {
            emailEdtText.error = null
        }
        if (!UtilValidators.isValidPassword(password)) {
            passwordEdtText.error = "Invalid Password"
            return
        } else {
            passwordEdtText.error = null
        }
        if (loginUser(email, password)) {
            goToHomeActivity()
        }
    }

    private fun loginUser(email: String, password: String): Boolean {
        val loginUserRequest = LoginUserRequest(email, password)
        loginViewModel.loginUser(loginUserRequest)
        return true
    }

    private fun save(key: String, text: EditText) {
        val sharedPreferences =
            requireContext().getSharedPreferences("mainPrefs", Context.MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()
        myEdit.putString(key, text.text.toString())
        myEdit.apply()
    }

    private fun delete() {
        val sharedPreferences =
            requireContext().getSharedPreferences("mainPrefs", Context.MODE_PRIVATE)
        val myEdit = sharedPreferences.edit().clear().commit()
    }

    companion object {
        const val TAG_LOGIN = "TAG_LOGIN"
        fun newInstance(): LoginFragment {
            val args = Bundle()
            val fragment = LoginFragment()
            fragment.arguments = args
            return fragment
        }
    }
}