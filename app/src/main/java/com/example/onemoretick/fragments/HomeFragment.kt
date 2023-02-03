package com.example.onemoretick.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onemoretick.R
import com.example.onemoretick.adapters.CategoryAdapter
import com.example.onemoretick.adapters.TaskAdapter
import com.example.onemoretick.fragments.EditTaskFragment.Companion.TAG_EDIT_TASK
import com.example.onemoretick.interfaces.ActivitiesFragmentsCommunication
import com.example.onemoretick.interfaces.OnCategoryItemClick
import com.example.onemoretick.models.category.Category
import com.example.onemoretick.models.request.DeleteTaskRequest
import com.example.onemoretick.models.request.EditTaskRequest
import com.example.onemoretick.models.result.TaskResponse
import com.example.onemoretick.viewModel.DeleteTaskViewModel
import com.example.onemoretick.viewModel.EditTaskViewModel
import com.example.onemoretick.viewModel.GetCategoriesViewModel
import com.example.onemoretick.viewModel.GetTasksViewModel

class HomeFragment(private var userId: Int) : Fragment(), TaskAdapter.OnItemClickListener {
    private var fragmentsCommunication: ActivitiesFragmentsCommunication? = null
    private var categories: ArrayList<Category> = arrayListOf()
    private var tasks: ArrayList<TaskResponse> = arrayListOf()
    private var categoryAdapter: CategoryAdapter =
        CategoryAdapter(categories, object : OnCategoryItemClick {
            override fun categoryItemClick(category: Category?) {
                // TODO: Process category click
            }
        })
    private var taskAdapter: TaskAdapter = TaskAdapter(tasks, this)
    private val getCategoriesViewModel by viewModels<GetCategoriesViewModel>()
    private val getTasksViewModel by viewModels<GetTasksViewModel>()
    private val editTaskViewModel by viewModels<EditTaskViewModel>()
    private val deleteTaskViewModel by viewModels<DeleteTaskViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ActivitiesFragmentsCommunication) {
            fragmentsCommunication = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpCategoryRecyclerView(view.findViewById(R.id.categories_list))
        setUpTaskRecyclerView(view.findViewById(R.id.tasks_list))

        getCategoriesViewModel.getCategories()
        getCategoriesViewModel.getCategoriesSuccess.observe(viewLifecycleOwner) { categoriesResponse ->
            categories.clear()
            categories.add(Category(0, "All"))
            categoriesResponse.onEach { c -> categories.add(Category(c.id, c.name)) }
            categoryAdapter.notifyDataSetChanged()
        }
        getCategoriesViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, "Error retrieving categories", Toast.LENGTH_SHORT).show()
        }

        getTasksViewModel.getTasks(userId)
        getTasksViewModel.getTasksSuccess.observe(viewLifecycleOwner) { tasksResponse ->
            tasks.clear()
            tasksResponse.onEach { t -> tasks.add(t) }
            taskAdapter.notifyDataSetChanged()
        }
        getTasksViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, "Error retrieving tasks", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpCategoryRecyclerView(recyclerView: RecyclerView) {
        val layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = categoryAdapter
    }

    private fun setUpTaskRecyclerView(recyclerView: RecyclerView) {
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = taskAdapter
    }

    private fun deleteTask(deleteTaskRequest: DeleteTaskRequest) {
        deleteTaskViewModel.deleteTask(deleteTaskRequest)
        deleteTaskViewModel.deleteTaskSuccess.observe(viewLifecycleOwner) {
            getTasksViewModel.getTasks(userId)
        }
        deleteTaskViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, "Error deleting task", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCheckBoxClick(task: TaskResponse, isChecked: Boolean) {
        val editTaskRequest = EditTaskRequest(
            task.id,
            task.title,
            task.description,
            task.startDate,
            task.endDate,
            if (isChecked) 1 else 0,
            task.idCategory,
            task.idUser
        )
        editTaskViewModel.editTask(editTaskRequest)
    }

    override fun onEditClick(task: TaskResponse) {
        fragmentsCommunication?.onReplaceFragment(TAG_EDIT_TASK, task = task)
    }

    override fun onDeleteClick(task: TaskResponse) {
        deleteTask(DeleteTaskRequest(task.idUser, task.id))
    }

    companion object {
        const val TAG_HOME = "TAG_HOME"
        fun newInstance(userId: Int): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment(userId)
            fragment.arguments = args
            return fragment
        }
    }
}