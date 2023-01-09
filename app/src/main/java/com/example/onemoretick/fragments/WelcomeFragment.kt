package com.example.onemoretick.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.onemoretick.R
import com.example.onemoretick.interfaces.ActivitiesFragmentsCommunication

class WelcomeFragment : Fragment() {
    private var fragmentsCommunication: ActivitiesFragmentsCommunication? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ActivitiesFragmentsCommunication) {
            fragmentsCommunication = context as ActivitiesFragmentsCommunication
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.login_button).setOnClickListener { goToLoginFragment() }
        view.findViewById<View>(R.id.register_button).setOnClickListener { goToRegisterFragment() }
    }

    //    private fun goToLoginFragment() {
//        if (fragmentsCommunication != null) {
//            fragmentsCommunication.onReplaceFragment(LoginFragment.TAG_LOGIN)
//        }
//    }
    private fun goToLoginFragment() {
//    fragmentsCommunication?.onReplaceFragment(LoginFragment.TAG_LOGIN)
    }

    private fun goToRegisterFragment() {
//        fragmentsCommunication?.onReplaceFragment(RegisterFragment.TAG_REGISTER)
    }

    companion object {
        const val TAG_WELCOME = "TAG_WELCOME"
        fun newInstance(): WelcomeFragment {
            val args = Bundle()
            val fragment: WelcomeFragment = WelcomeFragment()
            fragment.arguments = args
            return fragment
        }
    }
}