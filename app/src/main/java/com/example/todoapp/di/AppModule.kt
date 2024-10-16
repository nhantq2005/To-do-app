package com.example.todoapp.di

import android.app.Application
import androidx.room.Room
import com.example.todoapp.feature_todo.data.data_source.TaskDatabase
import com.example.todoapp.feature_todo.data.repository.TaskRepositoryImpl
import com.example.todoapp.feature_todo.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTaskDatabase(app:Application):TaskDatabase{
        return Room.databaseBuilder(
            app,
            TaskDatabase::class.java,
            TaskDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(db:TaskDatabase):TaskRepository{
        return TaskRepositoryImpl(db.taskDao)
    }

    @Provides
    @Singleton
    fun provideTaskUseCases(repository: TaskRepository):TaskUseCase{

    }
}