package com.example.onemoretick.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.onemoretick.R
import com.example.onemoretick.interfaces.ActivitiesFragmentsCommunication
import com.example.onemoretick.models.request.CreateTaskRequest
import com.example.onemoretick.viewModel.CreateTaskViewModel


class CreateTaskFragment(private var userId: Int) : Fragment() {
    private var fragmentsCommunication: ActivitiesFragmentsCommunication? = null
    private var categoriesList: ArrayList<String?> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_task, container, false)
    }

    private val createTaskViewModel by viewModels<CreateTaskViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ActivitiesFragmentsCommunication) {
            fragmentsCommunication = context as ActivitiesFragmentsCommunication
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        val editTextName = view.findViewById<EditText>(R.id.name_text_input_editText)
        val editTextDescription = view.findViewById<EditText>(R.id.description_text_input_editText)
        val editTextStartDate = view.findViewById<EditText>(R.id.start_date_text_input_editText)
        val editTextEndDate = view.findViewById<EditText>(R.id.end_date_text_input_editText)
        val editTextCategory =
            view.findViewById<AppCompatAutoCompleteTextView>(R.id.categories_text_view)
        view.findViewById<View>(R.id.create_task_button).setOnClickListener {
            createTask(
                editTextName.text.toString(),
                editTextDescription.text.toString(),
                editTextStartDate.text.toString(),
                editTextEndDate.text.toString(),
                categoriesList.indexOf(editTextCategory.text?.toString()) + 1,
                userId
            )
        }
    }

    private fun initUI() {
        //UI reference of textView
        val categoriesAutoTV: AutoCompleteTextView =
            requireView().findViewById(R.id.categories_text_view)

        // create list of customer
        categoriesList = getCategoriesList()

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

    private fun createTask(
        name: String,
        description: String,
        startDate: String,
        endDate: String,
        idCategory: Int,
        idUser: Int
    ): Boolean {
        Toast.makeText(
            context,
            "$name $description $startDate $endDate",
            Toast.LENGTH_SHORT
        ).show();
        val createTaskRequest =
            CreateTaskRequest(name, description, startDate, endDate, 0, idCategory, idUser)
        createTaskViewModel.createTask(createTaskRequest)
        return true
    }

    companion object {
        const val TAG_CREATE_TASK = "TAG_CREATE_TASK"
        fun newInstance(userId: Int): CreateTaskFragment {
            val args = Bundle()
            val fragment = CreateTaskFragment(userId)
            fragment.arguments = args
            return fragment
        }
    }
}