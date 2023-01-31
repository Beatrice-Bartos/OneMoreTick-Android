package com.example.onemoretick.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.onemoretick.R
import com.example.onemoretick.interfaces.ActivitiesFragmentsCommunication

class TaskActivity : AppCompatActivity(), ActivitiesFragmentsCommunication {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
    }

    override fun onReplaceFragment(TAG: String?, userId: Int?) {
        TODO("Not yet implemented")
    }
}