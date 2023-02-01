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
import com.example.onemoretick.models.result.TaskResponse
import com.example.onemoretick.viewModel.GetTasksViewModel

class HomeFragment(private var userId: Int) : Fragment() {
    public var selectedTask: TaskResponse? = null
    private var fragmentsCommunication: ActivitiesFragmentsCommunication? = null
    private var categories: ArrayList<Category> = arrayListOf()
    private var tasks: ArrayList<TaskResponse> = arrayListOf()
    private var categoryAdapter: CategoryAdapter =
        CategoryAdapter(categories, object : OnCategoryItemClick {
            override fun categoryItemClick(category: Category?) {
                // TODO: Process category click
            }
        })
    private var taskAdapter: TaskAdapter = TaskAdapter(this, tasks)
    private val getTasksViewModel by viewModels<GetTasksViewModel>()

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

        // TODO: Read categories
        categories.add(Category(0, "All"))
        categories.add(Category(1, "Work"))
        categories.add(Category(2, "School"))
        categories.add(Category(3, "Home"))
        categories.add(Category(4, "Other"))
        categoryAdapter.notifyDataSetChanged()

        getTasksViewModel.getTasks(userId)
        getTasksViewModel.getTasksSuccess.observe(viewLifecycleOwner) { tasksResponse ->
            tasksResponse.onEach { t -> tasks.add(t) }
            taskAdapter.notifyDataSetChanged()
        }
        getTasksViewModel.error.observe(viewLifecycleOwner) { errorResponse ->
            Toast.makeText(context, "Error retrieving tasks", Toast.LENGTH_SHORT).show();
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

    fun onClickTaskEditButton(v: View) {
        fragmentsCommunication?.onReplaceFragment(TAG_EDIT_TASK, task = selectedTask)
    }

    fun onClickTaskDeleteButton(v: View) {

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