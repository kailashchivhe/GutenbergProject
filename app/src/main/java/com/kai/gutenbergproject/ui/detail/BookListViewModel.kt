package com.kai.gutenbergproject.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kai.gutenbergproject.model.CategoryEnum
import com.kai.gutenbergproject.model.Gutenberg
import com.kai.gutenbergproject.network.BookSingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookListViewModel : ViewModel() {

    companion object
    {
        const val TAG = "BookListViewModel"
    }

    fun getBooksByCategory( categoryEnum: CategoryEnum )
    {
        BookSingleton.getBooksByCategory(
            categoryEnum,
            object : retrofit2.Callback<Gutenberg>
            {
                override fun onResponse( call: Call<Gutenberg>, response: Response<Gutenberg> )
                {
                    Log.d( TAG, "onResponse:  " + response.body() )
                }

                override fun onFailure( call: Call<Gutenberg>, t: Throwable )
                {
                    Log.d( TAG, "onFailure: ${t.cause}" )
                }
            } )
    }

    fun getBooksByQuery( query: String )
    {
        BookSingleton.getBooksByQuery(
            query,
            object : Callback<Gutenberg>
            {
                override fun onResponse( call: Call<Gutenberg>, response: Response<Gutenberg> )
                {
                    Log.d( TAG, "onResponse:  " + response.body() )
                }

                override fun onFailure( call: Call<Gutenberg>, t: Throwable )
                {
                    Log.d( TAG, "onFailure: ${t.cause}" )
                }
            } )
    }
}