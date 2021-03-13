package com.kai.gutenbergproject.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kai.gutenbergproject.MainActivity.Companion.GENRE_ARGS
import com.kai.gutenbergproject.R


class BookListFragment : Fragment()
{
    companion object
    {
        fun newInstance() = BookListFragment()
    }

    private lateinit var viewModel: BookListViewModel

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View?
    {
        return inflater.inflate( R.layout.book_list_fragment, container, false )
    }

    override fun onActivityCreated( savedInstanceState: Bundle? )
    {
        super.onActivityCreated( savedInstanceState )
        viewModel = ViewModelProvider(this).get( BookListViewModel::class.java )
        initActionBar()
        setHasOptionsMenu( true )
    }

    private fun initActionBar()
    {
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar?.title = arguments?.getString(GENRE_ARGS)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when ( item.itemId )
        {
            android.R.id.home->
            {
                findNavController().navigateUp()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}