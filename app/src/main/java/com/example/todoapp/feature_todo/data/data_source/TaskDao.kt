package com.example.todoapp.feature_todo.data.data_source

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todoapp.feature_todo.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskDao {
    @Query("SELECT * FROM task_table")
    fun getTask(): Flow<List<Task>>

    @Query("SELECT * FROM task_table WHERE id=:id")
    suspend fun getTaskById(id:Int):Task?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
}