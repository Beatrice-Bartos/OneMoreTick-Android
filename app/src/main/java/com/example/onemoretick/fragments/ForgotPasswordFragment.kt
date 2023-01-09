package com.example.onemoretick.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.onemoretick.R
import com.example.onemoretick.helpers.UtilValidators
import com.example.onemoretick.interfaces.ActivitiesFragmentsCommunication
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordFragment : Fragment() {
    private var fragmentsCommunication: ActivitiesFragmentsCommunication? = null

    //private var auth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //auth = FirebaseAuth.getInstance()
    }

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
        if (!UtilValidators.isValidEmail(email)) {
            emailEdtText.error = "Invalid Email"
            return
        } else {
            emailEdtText.error = null
        }
        resetPassword(email)
    }

    //    private fun resetPassword(email: String) {
//        auth!!.sendPasswordResetEmail(email)
//            .addOnCompleteListener(object : OnCompleteListener<Any?> {
//                override fun onComplete(task: Task<*>) {
//                    if (task.isSuccessful) {
//                        Toast.makeText(
//                            context,
//                            "The email was sent successfully!",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        goToLoginFragment()
//                    } else {
//                        Toast.makeText(
//                            context,
//                            "The email was not sent successfully!",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
//            })
//    }
    private fun resetPassword(email: String) {
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
