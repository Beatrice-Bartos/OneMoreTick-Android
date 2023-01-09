package com.example.onemoretick.fragments

import android.content.Context
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
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {
    private var fragmentsCommunication: ActivitiesFragmentsCommunication? = null

    // private var auth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //auth = FirebaseAuth.getInstance()
    }

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
//        val sharedPreferences = requireContext().getSharedPreferences("mainPrefs", Context.MODE_PRIVATE)
//        if (sharedPreferences.contains("Email") && sharedPreferences.contains("Password")) {
//            rememberMe.isChecked = true
//            val emailStr = sharedPreferences.getString("Email", "")
//            val passwordStr = sharedPreferences.getString("Password", "")
//            editTextEmail.setText(emailStr)
//            editTextPassword.setText(passwordStr)
//        }
        view.findViewById<View>(R.id.login_button).setOnClickListener { validateEmailAndPassword() }
        view.findViewById<View>(R.id.forgotPassword_textView)
            .setOnClickListener { goToForgotPasswordFragment() }
        view.findViewById<View>(R.id.register_textView)
            .setOnClickListener { goToRegisterFragment() }
//        rememberMe.setOnCheckedChangeListener { compoundButton, b ->
//            if (compoundButton.isChecked) {
//                save("Email", editTextEmail)
//                save("Password", editTextPassword)
//            } else if (!compoundButton.isChecked) {
//                delete()
//                delete()
//            }
//        }
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
//        val intent = Intent(activity, HomeMapActivity::class.java)
//        startActivity(intent)
//        activity!!.finish()
        Toast.makeText(context, "Login success!", Toast.LENGTH_SHORT).show();
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
        //loginFirebaseUser(email, password)
        goToHomeActivity()
    }

//    private fun loginFirebaseUser(email: String, password: String) {
//        auth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                goToHomeMapActivity()
//                Toast.makeText(context, "Authentication success", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

//    private fun save(key: String, text: EditText) {
//        val sharedPreferences = context!!.getSharedPreferences("mainPrefs", Context.MODE_PRIVATE)
//        val myEdit = sharedPreferences.edit()
//        myEdit.putString(key, text.text.toString())
//        myEdit.apply()
//    }
//
//    private fun delete() {
//        val sharedPreferences = context!!.getSharedPreferences("mainPrefs", Context.MODE_PRIVATE)
//        val myEdit = sharedPreferences.edit().clear().commit()
//    }

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