package com.example.tasksapplicationfinalversion.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface TaskDao {

    @Insert //(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(taskEntity: TaskEntity)

    @Delete
    suspend fun delete(taskEntity: TaskEntity)

    @Query("SELECT * FROM  tasksTable")
    fun getTasks(): LiveData<List<TaskEntity>>

    @Query("SELECT * FROM tasksTable WHERE id = :id")
    fun getToDoById (id: Int): LiveData<TaskEntity>
}