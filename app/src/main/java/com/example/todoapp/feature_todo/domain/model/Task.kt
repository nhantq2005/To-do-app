package com.example.todoapp.feature_todo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task (
    @PrimaryKey(autoGenerate = true)
    val id:Int? = 0,
    val title:String,
    val detail:String,
    val timeStamp:Long,
    val isImportant:Boolean,
    var isDone:Boolean
)

class InvalidTaskException(message:String):Exception(message){

}