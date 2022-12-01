package com.example.tasksapplicationfinalversion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasksapplicationfinalversion.SeparateTaskFragmentArgs
import com.example.tasksapplicationfinalversion.data.TaskDao
import com.example.tasksapplicationfinalversion.data.TaskEntity
import kotlinx.coroutines.launch

class TasksViewModel(private val dao: TaskDao) : ViewModel() {

    val tasks = dao.getTasks()
    suspend fun insertNewTask(taskEntity: TaskEntity) = viewModelScope.launch {
        dao.insert(taskEntity)
    }
    suspend fun removeNewTask(taskEntity: TaskEntity) = dao.delete(taskEntity)

    fun getTaskById(id: Int) : LiveData<TaskEntity> {
        return dao.getToDoById(id)
    }
}