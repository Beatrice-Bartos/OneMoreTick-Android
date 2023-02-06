package com.example.onemoretick.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.onemoretick.R
import com.example.onemoretick.activities.HomeActivity
import com.example.onemoretick.helpers.UtilValidators
import com.example.onemoretick.interfaces.ActivitiesFragmentsCommunication
import com.example.onemoretick.models.request.RegisterUserRequest
import com.example.onemoretick.models.result.LoginUserResponse
import com.example.onemoretick.models.result.RegisterUserResponse
import com.example.onemoretick.viewModel.RegisterViewModel

class RegisterFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val registerViewModel by viewModels<RegisterViewModel>()

    override fun onStart() {
        super.onStart()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ActivitiesFragmentsCommunication) {
            val fragmentsCommunication: ActivitiesFragmentsCommunication =
                context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.sign_up_button)
            .setOnClickListener { validateEmailAndPassword() }

        registerViewModel.registerSuccess.observe(viewLifecycleOwner){ registerResponse ->
            goToHomeActivity(registerResponse)
        }
        registerViewModel.error.observe(viewLifecycleOwner){
//            Toast.makeText(context, "Error logging in", Toast.LENGTH_SHORT).show();
        }
    }

    private fun validateEmailAndPassword() {
        if (view == null) {
            return
        }
        val emailEdtText = requireView().findViewById<EditText>(R.id.email_text_input_editText)
        val passwordEdtText =
            requireView().findViewById<EditText>(R.id.password_text_input_editText)
        val email = emailEdtText.text.toString()
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
        registerUser(email, password)
    }

    private fun registerUser(email: String, password: String): Boolean {
        val registerUserRequest = RegisterUserRequest(email, password)
        registerViewModel.registerUser(registerUserRequest)

        return true
    }

    private fun goToHomeActivity(registerResponse: LoginUserResponse) {
        val intent = Intent(activity, HomeActivity::class.java)
        intent.putExtra("Response", registerResponse)
        startActivity(intent)
        requireActivity().finish()
    }

    companion object {
        const val TAG_REGISTER = "TAG_REGISTER"
        fun newInstance(): RegisterFragment {
            val args = Bundle()
            val fragment = RegisterFragment()
            fragment.arguments = args
            return fragment
        }
    }
}