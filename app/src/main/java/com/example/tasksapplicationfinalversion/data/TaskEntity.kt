package com.example.tasksapplicationfinalversion.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasksTable")
data class TaskEntity(
    @ColumnInfo(name = "task")
    val taskName: String,
    val isCompleted: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)