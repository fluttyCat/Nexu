package com.nexu.core.database.di

import android.content.Context
import androidx.room.Room
import com.nexu.core.database.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesTodoDatabase(
        @ApplicationContext context: Context,
    ): TodoDatabase = Room.databaseBuilder(
        context,
        TodoDatabase::class.java,
        "todo-database",
    ).build()
}
