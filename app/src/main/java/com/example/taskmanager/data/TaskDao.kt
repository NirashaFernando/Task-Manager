package com.example.taskmanager.data

import androidx.room.*


@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<Task>

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM tasks WHERE name LIKE '%' || :searchQuery || '%'")
    suspend fun searchTasksByName(searchQuery: String): List<Task>

}
