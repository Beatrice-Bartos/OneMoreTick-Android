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
import com.example.onemoretick.fragments.HomeFragment.Companion.TAG_HOME
import com.example.onemoretick.helpers.UtilValidators
import com.example.onemoretick.interfaces.ActivitiesFragmentsCommunication
import com.example.onemoretick.models.request.EditTaskRequest
import com.example.onemoretick.models.result.TaskResponse
import com.example.onemoretick.viewModel.EditTaskViewModel

class EditTaskFragment(private val task: TaskResponse) : Fragment() {
    private var fragmentsCommunication: ActivitiesFragmentsCommunication? = null
    private var categoriesList: ArrayList<String?> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_task, container, false)
    }

    private val editTaskViewModel by viewModels<EditTaskViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ActivitiesFragmentsCommunication) {
            fragmentsCommunication = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()

        val editTextName = view.findViewById<EditText>(R.id.edit_task_name_text_input_editText)
        val editTextDescription =
            view.findViewById<EditText>(R.id.edit_task_description_text_input_editText)
        val editTextStartDate =
            view.findViewById<EditText>(R.id.edit_task_start_date_text_input_editText)
        val editTextEndDate =
            view.findViewById<EditText>(R.id.edit_task_end_date_text_input_editText)
        val editTextCategory =
            view.findViewById<AppCompatAutoCompleteTextView>(R.id.edit_task_categories_text_view)

        editTextName.setText(task.title)
        editTextDescription.setText(task.description)
        editTextStartDate.setText(task.startDate)
        editTextEndDate.setText(task.endDate)

        view.findViewById<View>(R.id.edit_task_button).setOnClickListener {
            if (!UtilValidators.isValidDate(editTextStartDate.text.toString())) {
                editTextStartDate.error = "Date format is invalid\nUse [yyyy-mm-dd]"
            } else if (!UtilValidators.isValidDate(editTextEndDate.text.toString())) {
                editTextEndDate.error = "Date format is invalid\nUse [yyyy-mm-dd]"
            } else {
                val editTaskRequest = EditTaskRequest(
                    task.id,
                    editTextName.text.toString(),
                    editTextDescription.text.toString(),
                    editTextStartDate.text.toString(),
                    editTextEndDate.text.toString(),
                    task.isDone,
                    categoriesList.indexOf(editTextCategory.text?.toString()) + 1,
                    task.idUser
                )
                editTaskViewModel.editTask(editTaskRequest)
            }
        }

        editTaskViewModel.editTaskSuccess.observe(viewLifecycleOwner) {
            fragmentsCommunication?.onReplaceFragment(TAG_HOME, task.idUser)
        }
        editTaskViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, "Error saving task", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initUI() {
        val categoriesAutoTV: AutoCompleteTextView =
            requireView().findViewById(R.id.edit_task_categories_text_view)

        categoriesList = getCategoriesList()

        val adapter: ArrayAdapter<String?> = ArrayAdapter(
            this.requireContext(),
            android.R.layout.simple_spinner_item,
            categoriesList
        )

        categoriesAutoTV.setAdapter(adapter)
        categoriesAutoTV.setText(categoriesAutoTV.adapter.getItem(task.idCategory - 1).toString())
        adapter.filter.filter(null)
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
        const val TAG_EDIT_TASK = "TAG_EDIT_TASK"
        fun newInstance(task: TaskResponse): EditTaskFragment {
            val args = Bundle()
            val fragment = EditTaskFragment(task)
            fragment.arguments = args
            return fragment
        }
    }
}