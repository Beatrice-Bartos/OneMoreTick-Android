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
import com.example.onemoretick.models.request.CreateTaskRequest
import com.example.onemoretick.viewModel.CreateTaskViewModel
import com.example.onemoretick.viewModel.GetCategoriesViewModel

class CreateTaskFragment(private var userId: Int) : Fragment() {
    private var fragmentsCommunication: ActivitiesFragmentsCommunication? = null
    private var categoriesList: ArrayList<String?> = arrayListOf()
    private lateinit var adapter: ArrayAdapter<String?>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_task, container, false)
    }

    private val getCategoriesViewModel by viewModels<GetCategoriesViewModel>()
    private val createTaskViewModel by viewModels<CreateTaskViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ActivitiesFragmentsCommunication) {
            fragmentsCommunication = context
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
            if (!UtilValidators.isValidDate(editTextStartDate.text.toString())) {
                editTextStartDate.error = "Date format is invalid. Use [yyyy-mm-dd]"
            } else if (!UtilValidators.isValidDate(editTextEndDate.text.toString())) {
                editTextEndDate.error = "Date format is invalid. Use [yyyy-mm-dd]"
            } else {
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

        createTaskViewModel.createTaskSuccess.observe(viewLifecycleOwner) { createTaskResponse ->
            fragmentsCommunication?.onReplaceFragment(TAG_HOME, createTaskResponse.idUser)
        }
        createTaskViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, "Error creating task", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initUI() {
        val categoriesAutoTV: AutoCompleteTextView =
            requireView().findViewById(R.id.categories_text_view)

        getCategoriesViewModel.getCategories()
        getCategoriesViewModel.getCategoriesSuccess.observe(viewLifecycleOwner) { categoriesResponse ->
            categoriesList.clear()
            categoriesResponse.onEach { c -> categoriesList.add(c.name) }
            adapter.notifyDataSetChanged()
        }
        getCategoriesViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, "Error retrieving categories", Toast.LENGTH_SHORT).show()
        }

        adapter = ArrayAdapter(
            this.requireContext(),
            android.R.layout.simple_spinner_item,
            categoriesList
        )

        categoriesAutoTV.setAdapter(adapter)
    }

    private fun createTask(
        name: String,
        description: String,
        startDate: String,
        endDate: String,
        idCategory: Int,
        idUser: Int
    ): Boolean {
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