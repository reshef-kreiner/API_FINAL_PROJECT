package com.example.api_movie_app.di

import android.content.Context
import com.example.api_movie_app.data.local_db.AppDatabase
import com.example.api_movie_app.data.remote_db.MovieService
import com.example.api_movie_app.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGson() : Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideRetrofit(gson : Gson) : Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
    }

    @Provides
    fun provideMovieService(retrofit: Retrofit) : MovieService =
        retrofit.create(MovieService::class.java)

    @Provides
    @Singleton
    fun provideLocalDataBase(@ApplicationContext appContext : Context) : AppDatabase =
        AppDatabase.getDatabase(appContext)

    @Provides
    @Singleton
    fun provideMovieDao(database: AppDatabase) = database.movieDao()

//    @Provides
//    @Singleton
//    fun provideFavoriteMovieDao(database: AppDatabase) = database.favoriteMovieDao()
}