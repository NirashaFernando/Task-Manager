package com.example.taskmanager

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.taskmanager.data.Task
import com.example.taskmanager.viewmodels.AddTaskViewModel

class AddTaskActivity : AppCompatActivity() {

    private lateinit var viewModel: AddTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        supportActionBar?.hide()

        viewModel = ViewModelProvider(this).get(AddTaskViewModel::class.java)
        val addTaskButton: Button = findViewById(R.id.saveTaskButton)
        addTaskButton.setOnClickListener {
            val nameEditText: EditText = findViewById(R.id.taskNameEditText)
            val descriptionEditText: EditText = findViewById(R.id.taskDescriptionEditText)
            val priorityEditText: EditText = findViewById(R.id.taskPriorityEditText)
            val deadlineEditText: EditText = findViewById(R.id.taskDeadlineEditText)

            val name = nameEditText.text.toString().trim()
            val description = descriptionEditText.text.toString().trim()
            val priority = priorityEditText.text.toString().trim()
            val deadline = deadlineEditText.text.toString().trim()

            if (name.isNotEmpty() && description.isNotEmpty() && priority.isNotEmpty()  && deadline.isNotEmpty()) {
                if (priority == "1" || priority == "2" || priority == "3") {
                    val task = Task(name = name, description = description, priority = priority, deadline = deadline)
                    viewModel.addTask(task)
                    Toast.makeText(this, "Task added successfully", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Please enter a valid priority number (1, 2, or 3)", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
