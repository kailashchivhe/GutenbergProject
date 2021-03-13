package com.kai.gutenbergproject.network

import com.kai.gutenbergproject.model.CategoryEnum
import com.kai.gutenbergproject.model.Gutenberg
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BookSingleton
{
    private const val mBaseURL = "https://gutendex.com/"

    lateinit var mCurrentCategoryEnum: CategoryEnum

    private val mRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl( mBaseURL )
        .addConverterFactory( GsonConverterFactory.create() )
        .build()

    private val mBookServiceInterface = mRetrofit.create( BookServiceInterface::class.java )

    fun getBooksByCategory( categoryEnum: CategoryEnum, callback: Callback<Gutenberg> )
    {
        mBookServiceInterface.getBooksByCategory( categoryEnum.name ).enqueue( callback )
    }

    fun getBooksByQuery( query: String, callback: Callback<Gutenberg> )
    {
        mBookServiceInterface.getBooksByQuery( mCurrentCategoryEnum.name, query ).enqueue( callback )
    }
}