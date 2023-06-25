package com.example.api_movie_app.data.remote_db

import android.util.Log
import com.example.api_movie_app.utils.Resource
import retrofit2.Response
import java.lang.Exception

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call : suspend () -> Response<T>) : Resource<T> {

        try {
            val result = call()
            if (result.isSuccessful) {
                Log.d("Result Call", "Result call is GOOD")
                val body = result.body()
                if (body != null) return Resource.success(body)
            }
            Log.d("Result Call", "Result call is BAD")
            return Resource.error("Network call has failed for the following reason: " +
                    "${result.message()} ${result.code()}")
        } catch (e : Exception) {
            Log.d("Result Call", "Result call is ERROR")
            e.printStackTrace();
            return Resource.error("Network call has failed for the following reason: " +
                    (e.localizedMessage ?: e.toString()))
        }

    }
}
