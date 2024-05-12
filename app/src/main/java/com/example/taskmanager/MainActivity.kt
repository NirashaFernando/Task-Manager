package com.example.taskmanager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.taskmanager.AddTaskActivity
import com.example.taskmanager.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val addTaskButton: Button = findViewById(R.id.addTaskButton)
        val viewTaskButton: Button = findViewById(R.id.viewTasksButton)

        addTaskButton.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }

        viewTaskButton.setOnClickListener {
            startActivity(Intent(this, ViewTasksActivity::class.java))
        }
    }
}
