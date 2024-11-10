package com.example.todoapp.feature_todo.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.feature_todo.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table")
    fun getTask(): Flow<List<Task>>

//    @Query("SELECT * FROM task_table WHERE isDone=:isDone")
//    suspend fun getImportantTask(isDone:Boolean):Flow<List<Task>>

    @Query("SELECT * FROM task_table WHERE id=:id")
    suspend fun getTaskById(id:Int):Task?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
}