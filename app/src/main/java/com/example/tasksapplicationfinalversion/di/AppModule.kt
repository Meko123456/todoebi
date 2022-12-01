package com.example.tasksapplicationfinalversion.di

import android.app.Application
import androidx.room.Room
import com.example.tasksapplicationfinalversion.data.TaskDao
import com.example.tasksapplicationfinalversion.data.TaskDataBase
import com.example.tasksapplicationfinalversion.viewmodel.TasksViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    fun provideDataBase(application: Application): TaskDataBase =
        Room.databaseBuilder(application, TaskDataBase::class.java, "USERDB")
            .fallbackToDestructiveMigration().build()

    fun provideDao(dataBase: TaskDataBase): TaskDao = dataBase.taskDao()

    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }

    viewModel {
        TasksViewModel(get())
    }

}