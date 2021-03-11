package com.kai.gutenbergproject.network

import com.kai.gutenbergproject.model.Gutenberg
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookServiceInterface
{
    @GET("books/?mime_type=image%2Fjpeg")
    fun getBooksByCategory( @Query("topic") category: String ): Call<Gutenberg>

    @GET("books/?mime_type=image%2Fjpeg")
    fun getBooksByQuery( @Query("topic") category: String, @Query("search") key: String ): Call<Gutenberg>
}