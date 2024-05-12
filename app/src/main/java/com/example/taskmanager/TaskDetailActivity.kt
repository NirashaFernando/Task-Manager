package com.example.taskmanager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.taskmanager.R
import com.example.taskmanager.data.Task
import com.example.taskmanager.viewmodels.AddTaskViewModel

class TaskDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: AddTaskViewModel

    private lateinit var taskNameEditText: EditText
    private lateinit var taskDescriptionEditText: EditText
    private lateinit var taskPriorityEditText: EditText
    private lateinit var taskDeadlineEditText: EditText
    private lateinit var updateTaskButton: Button
    private lateinit var deleteTaskButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_detail_layout)
        supportActionBar?.hide()
        viewModel = ViewModelProvider(this).get(AddTaskViewModel::class.java)
        taskNameEditText = findViewById(R.id.taskNameEditText)
        taskDescriptionEditText = findViewById(R.id.taskDescriptionEditText)
        taskPriorityEditText = findViewById(R.id.taskPriorityEditText)
        taskDeadlineEditText = findViewById(R.id.taskDeadlineEditText)
        updateTaskButton = findViewById(R.id.updateTaskButton)
        deleteTaskButton = findViewById(R.id.deleteTaskButton)


        val taskId = intent.getLongExtra("taskId", 0)
        val taskName = intent.getStringExtra("taskName") ?: ""
        val taskDescription = intent.getStringExtra("taskDescription") ?: ""
        val taskPriority = intent.getStringExtra("taskPriority") ?: ""
        val taskDeadline = intent.getStringExtra("taskDeadline") ?: ""


        taskNameEditText.setText(taskName)
        taskDescriptionEditText.setText(taskDescription)
        taskPriorityEditText.setText(taskPriority)
        taskDeadlineEditText.setText(taskDeadline)


        val name = taskNameEditText.text.toString().trim()
        val description = taskDescriptionEditText.text.toString().trim()
        val priority = taskPriorityEditText.text.toString().trim()
        val deadline = taskDeadlineEditText.text.toString().trim()

        updateTaskButton.setOnClickListener {
            val nameT = taskNameEditText.text.toString().trim()
            val descriptionT = taskDescriptionEditText.text.toString().trim()
            val priorityT = taskPriorityEditText.text.toString().trim()
            val deadlineT = taskDeadlineEditText.text.toString().trim()
            if (nameT.isNotEmpty() && descriptionT.isNotEmpty() && priority.isNotEmpty() && deadlineT.isNotEmpty()) {
                if (priority == "1" || priority == "2" || priority == "3") {
                    val task = Task(id = taskId ,name = nameT, description = descriptionT, priority = priorityT, deadline = deadlineT)
                    viewModel.updateTask(task)
                    Toast.makeText(this, "Task Updated successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else{
                    Toast.makeText(this, "Please enter a valid priority number (1, 2, or 3)", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
            }
        }

        deleteTaskButton.setOnClickListener {
            if (taskId != null) {
                val task = Task(id = taskId ,name = name, description = description, priority = priority, deadline = deadline)
                viewModel.deleteTask(task)
                Toast.makeText(this, "Task Deleted successfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
