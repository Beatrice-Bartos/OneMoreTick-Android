package com.example.onemoretick.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.onemoretick.R
import com.example.onemoretick.interfaces.ActivitiesFragmentsCommunication

class EditTaskFragment : Fragment() {
    private var fragmentsCommunication: ActivitiesFragmentsCommunication? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_task, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ActivitiesFragmentsCommunication) {
            fragmentsCommunication = context as ActivitiesFragmentsCommunication
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        const val TAG_EDIT_TASK = "TAG_EDIT_TASK"
        fun newInstance(userId: Int): EditTaskFragment {
            val args = Bundle()
            val fragment: EditTaskFragment = EditTaskFragment()
            fragment.arguments = args
            return fragment
        }
    }
}