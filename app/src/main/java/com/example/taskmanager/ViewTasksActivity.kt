package com.example.taskmanager

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.viewmodels.AddTaskViewModel

class ViewTasksActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: AddTaskViewModel
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_tasks)
        supportActionBar?.hide()
        recyclerView = findViewById(R.id.recyclerView)
        adapter = TaskAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this).get(AddTaskViewModel::class.java)

        viewModel.tasks.observe(this) { tasks ->
            adapter.submitList(tasks)
        }

        viewModel.getAllTasks()

        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.queryHint = "Search tasks..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.searchTasksByName(it) }
                return true
            }
        })
    }
}
