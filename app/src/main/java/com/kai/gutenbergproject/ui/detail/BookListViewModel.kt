package com.kai.gutenbergproject.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kai.gutenbergproject.model.CategoryEnum
import com.kai.gutenbergproject.model.Gutenberg
import com.kai.gutenbergproject.model.Result
import com.kai.gutenbergproject.network.BookSingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookListViewModel : ViewModel() {

    companion object
    {
        const val TAG = "BookListViewModel"
    }

    private var mBookList = MutableLiveData<MutableList<Result>>()

    fun getBooksByCategory( categoryEnum: CategoryEnum )
    {
        BookSingleton.mCurrentCategoryEnum = categoryEnum
        BookSingleton.getBooksByCategory(
            categoryEnum,
            object : retrofit2.Callback<Gutenberg>
            {
                override fun onResponse( call: Call<Gutenberg>, response: Response<Gutenberg> )
                {
                    mBookList.value = response.body()?.results
                    Log.d( TAG, "getBooksByCategory onResponse:  ${response.body()} " )
                }

                override fun onFailure( call: Call<Gutenberg>, t: Throwable )
                {
                    Log.d( TAG, "getBooksByCategory onFailure: ${t.cause}" )
                    mBookList.value = null
                }
            } )
    }

    fun getBookList(): MutableLiveData<MutableList<Result>>
    {
        return mBookList
    }

    fun getBooksByQuery( query: String )
    {
        BookSingleton.getBooksByQuery(
            query,
            object : Callback<Gutenberg>
            {
                override fun onResponse( call: Call<Gutenberg>, response: Response<Gutenberg> )
                {
                    mBookList.value = response.body()?.results
                    Log.d( TAG, "getBooksByQuery onResponse:  ${response.body()} " )
                }

                override fun onFailure( call: Call<Gutenberg>, t: Throwable )
                {
                    Log.d( TAG, "getBooksByQuery onFailure: ${t.cause}" )
                    mBookList.value = null
                }
            } )
    }
}