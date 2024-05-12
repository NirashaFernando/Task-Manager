package com.example.taskmanager

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.data.Task

class TaskAdapter(private val context: Context) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task)
        when (task.priority.toIntOrNull()) {
            1 -> holder.itemView.findViewById<CardView>(R.id.cardViewList).setCardBackgroundColor(ContextCompat.getColor(context, R.color.priority_high))
            2 -> holder.itemView.findViewById<CardView>(R.id.cardViewList).setCardBackgroundColor(ContextCompat.getColor(context, R.color.priority_normal))
            3 -> holder.itemView.findViewById<CardView>(R.id.cardViewList).setCardBackgroundColor(ContextCompat.getColor(context, R.color.priority_low))
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, TaskDetailActivity::class.java)
            intent.putExtra("taskId", task.id)
            intent.putExtra("taskName", task.name)
            intent.putExtra("taskDescription", task.description)
            intent.putExtra("taskPriority", task.priority)
            intent.putExtra("taskDeadline", task.deadline)
            context.startActivity(intent)
        }
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        private val deadlineTextView: TextView = itemView.findViewById(R.id.deadlineTextView)

        fun bind(task: Task) {
            nameTextView.text = task.name + "  @  :  "
            descriptionTextView.text = task.description
            deadlineTextView.text = task.deadline
        }
    }

    private class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }
}
