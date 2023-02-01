package com.example.onemoretick.interfaces

import com.example.onemoretick.models.result.TaskResponse

interface ActivitiesFragmentsCommunication {
    fun onReplaceFragment(TAG: String?, userId: Int? = null, task: TaskResponse? = null)

}