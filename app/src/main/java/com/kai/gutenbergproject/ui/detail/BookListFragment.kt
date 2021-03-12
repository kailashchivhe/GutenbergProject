package com.kai.gutenbergproject.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kai.gutenbergproject.R


class BookListFragment : Fragment()
{
    companion object
    {
        fun newInstance() = BookListFragment()
    }

    private lateinit var viewModel: BookListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val view = inflater.inflate(R.layout.book_list_fragment, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BookListViewModel::class.java)

        ( activity as AppCompatActivity).supportActionBar?.title = "Fiction"
        ( activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        ( activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)

        // TODO: Use the ViewModel
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onResume()
    {
        super.onResume()
    }
}