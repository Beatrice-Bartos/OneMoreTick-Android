package com.example.onemoretick.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onemoretick.R
import com.example.onemoretick.models.result.TaskResponse

class TaskAdapter(
    private val taskList: ArrayList<TaskResponse>,
    private val listener: OnItemClickListener
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

    inner class TaskViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val checkBoxCompleted: CheckBox = view.findViewById(R.id.check_box_completed)
        private val taskItemTitle: TextView = view.findViewById(R.id.task_name)
        private val taskItemStartDate: TextView = view.findViewById(R.id.task_start_date)
        private val taskItemEndDate: TextView = view.findViewById(R.id.task_end_date)
        private val taskItemDescription: TextView = view.findViewById(R.id.task_description)
        private val taskItemDeleteButton: ImageButton = view.findViewById(R.id.task_delete_btn)
        private val taskItemEditButton: ImageButton = view.findViewById(R.id.task_edit_btn)

        fun bind(taskResponse: TaskResponse) {
            val startDate = "${taskResponse.startDate} - "
            taskItemTitle.text = taskResponse.title
            taskItemStartDate.text = startDate
            taskItemEndDate.text = taskResponse.endDate
            taskItemDescription.text = taskResponse.description
            checkBoxCompleted.isChecked = taskResponse.isDone == 1

            checkBoxCompleted.setOnClickListener {
                listener.onCheckBoxClick(taskResponse, checkBoxCompleted.isChecked)
            }
            taskItemDeleteButton.setOnClickListener {
                listener.onDeleteClick(taskResponse)
            }
            taskItemEditButton.setOnClickListener {
                listener.onEditClick(taskResponse)
            }
        }
    }

    interface OnItemClickListener {
        fun onCheckBoxClick(task: TaskResponse, isChecked: Boolean)
        fun onEditClick(task: TaskResponse)
        fun onDeleteClick(task: TaskResponse)
    }
}