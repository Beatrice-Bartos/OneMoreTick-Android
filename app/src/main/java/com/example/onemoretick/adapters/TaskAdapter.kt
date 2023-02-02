package com.example.onemoretick.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.onemoretick.R
import com.example.onemoretick.fragments.HomeFragment
import com.example.onemoretick.models.request.DeleteTaskRequest
import com.example.onemoretick.models.result.TaskResponse
import com.example.onemoretick.viewModel.DeleteTaskViewModel
import com.example.onemoretick.viewModel.EditTaskViewModel

class TaskAdapter(
    private val homeFragment: HomeFragment,
    private val taskList: ArrayList<TaskResponse>
) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View =
            inflater.inflate(R.layout.task_item_cell, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task: TaskResponse = taskList[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    inner class TaskViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {
        private val taskItemTitle: TextView = view.findViewById(R.id.task_name)
        private val taskItemStartDate: TextView = view.findViewById(R.id.task_start_date)
        private val taskItemEndDate: TextView = view.findViewById(R.id.task_end_date)
        private val taskItemDescription: TextView = view.findViewById(R.id.task_description)
        private val taskItemDeleteButton: Button = view.findViewById(R.id.task_delete_btn)

        fun bind(task: TaskResponse) {
            taskItemTitle.text = task.title
            taskItemStartDate.text = task.startDate
            taskItemEndDate.text = task.endDate
            taskItemDescription.text = task.description
            taskItemDeleteButton.setOnClickListener {
                homeFragment.deleteTask(
                    DeleteTaskRequest(task.idUser, task.id)
                )
            }

            view.setOnClickListener { homeFragment.selectedTask = task }
        }
    }
}