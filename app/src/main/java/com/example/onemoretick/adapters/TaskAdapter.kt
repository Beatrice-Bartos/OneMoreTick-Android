package com.example.onemoretick.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onemoretick.R
import com.example.onemoretick.interfaces.OnItemClick
import com.example.onemoretick.models.task.Task

class TaskAdapter(
    private val taskList: ArrayList<Task>,
    private val onItemClick: OnItemClick?
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
        val task: Task = taskList[position]
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

        fun bind(task: Task) {
            taskItemTitle.text = task.title
            taskItemStartDate.text = task.startDate.toString()
            taskItemEndDate.text = task.endDate.toString()
            taskItemDescription.text = task.description

            view.setOnClickListener {
//                onItemClick?.taskItemClick(task)
            }
        }

    }

}