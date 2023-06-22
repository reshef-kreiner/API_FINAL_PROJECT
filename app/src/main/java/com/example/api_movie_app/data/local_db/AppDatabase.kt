package com.example.api_movie_app.data.local_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.api_movie_app.data.models.Movie
import com.example.api_movie_app.data.local_db.MovieDao

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"movies")
                    .fallbackToDestructiveMigration().build().also {
                        instance = it
                    }
            }
    }
}