package com.example.api_movie_app.data.remote_db

import com.example.api_movie_app.utils.Resource
import retrofit2.Response
import java.lang.Exception

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call : suspend () -> Response<T>) : Resource<T> {

        try {
            val result = call()
            if (result.isSuccessful) {
                val body = result.body()
                if (body != null) return Resource.success(body)
            }
            return Resource.error("Network call has failed for the following reason: " +
                    "${result.message()} ${result.code()}")
        } catch (e : Exception) {
            return Resource.error("Network call has failed for the following reason: " +
                    (e.localizedMessage ?: e.toString()))
        }

    }
}
