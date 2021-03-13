package com.kai.gutenbergproject.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kai.gutenbergproject.MainActivity.Companion.GENRE_ARGS
import com.kai.gutenbergproject.R
import com.kai.gutenbergproject.model.GenreItem
import com.kai.gutenbergproject.ui.adapter.GenreAdapter
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment()
{
    companion object
    {
        fun newInstance() = MainFragment()
    }

    private lateinit var mRecyclerView: RecyclerView

    private lateinit var mMainViewModel: MainViewModel

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View
    {
        return inflater.inflate( R.layout.main_fragment, container, false )
    }

    override fun onViewCreated( view: View, savedInstanceState: Bundle? )
    {
        super.onViewCreated( view, savedInstanceState )
        mRecyclerView = recycler_view
        mRecyclerView.setHasFixedSize( true )
        mRecyclerView.layoutManager = LinearLayoutManager( activity, LinearLayoutManager.VERTICAL, false )
    }

    override fun onActivityCreated( savedInstanceState: Bundle? )
    {
        super.onActivityCreated( savedInstanceState )
        mMainViewModel = ViewModelProvider(this).get( MainViewModel::class.java )
        ( activity as AppCompatActivity ).supportActionBar?.hide()
        mRecyclerView.adapter = GenreAdapter( mMainViewModel.getGenreList() )
        {
            onItemClick( it )
        }
    }

    private fun onItemClick(genreItem: GenreItem )
    {
        val bundle = bundleOf(GENRE_ARGS to  genreItem.mTitle )
        findNavController().navigate( R.id.action_mainFragment_to_bookListFragment, bundle )
    }
}