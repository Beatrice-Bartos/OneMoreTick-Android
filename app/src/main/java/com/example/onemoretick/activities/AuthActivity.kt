package com.example.onemoretick.activities

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.onemoretick.R
import com.example.onemoretick.fragments.ForgotPasswordFragment
import com.example.onemoretick.fragments.ForgotPasswordFragment.Companion.TAG_FORGOT_PASS
import com.example.onemoretick.fragments.LoginFragment
import com.example.onemoretick.fragments.LoginFragment.Companion.TAG_LOGIN
import com.example.onemoretick.fragments.RegisterFragment
import com.example.onemoretick.fragments.RegisterFragment.Companion.TAG_REGISTER
import com.example.onemoretick.fragments.WelcomeFragment
import com.example.onemoretick.fragments.WelcomeFragment.Companion.TAG_WELCOME
import com.example.onemoretick.interfaces.ActivitiesFragmentsCommunication
import com.example.onemoretick.models.result.TaskResponse

class AuthActivity : AppCompatActivity(), ActivitiesFragmentsCommunication {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        if (savedInstanceState == null) {
            onAddWelcomeFragment()
        }
    }

    private fun onAddWelcomeFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.auth_frame_layout, WelcomeFragment.newInstance(), TAG_WELCOME)
        fragmentTransaction.commit()
    }

    override fun onReplaceFragment(TAG: String?, userId: Int?, task: TaskResponse?) {
        val fragmentManager = supportFragmentManager

        val fragment: Fragment = when (TAG) {
            TAG_LOGIN -> {
                LoginFragment.newInstance()
            }
            TAG_REGISTER -> {
                RegisterFragment.newInstance()
            }
            TAG_FORGOT_PASS -> {
                ForgotPasswordFragment.newInstance()
            }
            TAG_WELCOME -> {
                WelcomeFragment.newInstance()
            }
            else -> return
        }
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.auth_frame_layout, fragment, TAG)

        fragmentTransaction.addToBackStack(TAG)
        fragmentTransaction.commit()
    }
}