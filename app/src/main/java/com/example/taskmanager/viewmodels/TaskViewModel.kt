package com.example.taskmanager.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.MyApplication
import com.example.taskmanager.data.Task
import com.example.taskmanager.data.TaskDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddTaskViewModel : ViewModel() {

    private val taskDatabase = TaskDatabase.getDatabase(MyApplication.instance)

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    fun getAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            val tasksList = taskDatabase.taskDao().getAllTasks()
            _tasks.postValue(tasksList)
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDatabase.taskDao().insertTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch((Dispatchers.IO)) {
            taskDatabase.taskDao().updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch((Dispatchers.IO)) {
            taskDatabase.taskDao().deleteTask(task)
        }
    }

    fun searchTasksByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val tasksList = taskDatabase.taskDao().searchTasksByName(name)
            _tasks.postValue(tasksList)
        }
    }



}
