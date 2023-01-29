package com.example.onemoretick.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import com.example.onemoretick.R
import com.example.onemoretick.interfaces.ActivitiesFragmentsCommunication


class CreateTaskFragment : Fragment() {
    private var fragmentsCommunication: ActivitiesFragmentsCommunication? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_task, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ActivitiesFragmentsCommunication) {
            fragmentsCommunication = context as ActivitiesFragmentsCommunication
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        //UI reference of textView
        val categoriesAutoTV: AutoCompleteTextView =
            requireView().findViewById(R.id.categories_text_view)

        // create list of customer
        val categoriesList = getCategoriesList()

        //Create adapter
        val adapter: ArrayAdapter<String?> = ArrayAdapter(
            this.requireContext(),
            android.R.layout.simple_spinner_item,
            categoriesList
        )

        //Set adapter
        categoriesAutoTV.setAdapter(adapter)
    }

    private fun getCategoriesList(): ArrayList<String?> {
        val categories: ArrayList<String?> = ArrayList()
        categories.add("Work")
        categories.add("School")
        categories.add("Home")
        categories.add("Other")
        return categories
    }

    companion object {
        const val TAG_CREATE_TASK = "TAG_CREATE_TASK"
        fun newInstance(): CreateTaskFragment {
            val args = Bundle()
            val fragment: CreateTaskFragment = CreateTaskFragment()
            fragment.arguments = args
            return fragment
        }
    }
}