package com.kai.gutenbergproject.ui.detail

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kai.gutenbergproject.MainActivity.Companion.GENRE_ARGS
import com.kai.gutenbergproject.R
import com.kai.gutenbergproject.model.GenreEnum
import com.kai.gutenbergproject.model.Result
import com.kai.gutenbergproject.ui.adapter.BookAdapter
import kotlinx.android.synthetic.main.book_list_fragment.*


class BookListFragment : Fragment()
{
    companion object
    {
        fun newInstance() = BookListFragment()
    }

    private lateinit var mRecyclerView: RecyclerView

    private lateinit var viewModel: BookListViewModel

    private lateinit var mAdapter: BookAdapter

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View?
    {
        return inflater.inflate( R.layout.book_list_fragment, container, false )
    }

    private fun loadData(genreEnum: GenreEnum )
    {
        viewModel.getBooksByCategory( genreEnum )
        activity?.let { fragmentActivity ->
            viewModel.getBookList().observe(fragmentActivity, Observer { bookList ->
                if (bookList != null) {
                    progressVisibility(View.GONE)
                    Log.d("temp", "loadData: " + bookList.size )
                    mAdapter.setData(bookList)
                    mAdapter.notifyDataSetChanged()
                } else {
                    showFailureToast();
                }
            })
        }
    }

    private fun showFailureToast()
    {
        Toast.makeText( context, R.string.failure_toast, Toast.LENGTH_LONG ).show()
    }

    private fun onItemClick( result: Result )
    {
        val uri = shouldPerformViewAction( result )
        if( uri.isNotEmpty() )
        {
            val intent = Intent( Intent.ACTION_VIEW, Uri.parse( uri ) )
            startActivity( intent )
        }
        else
        {
            showInvalidFormatDialog()
        }
    }

    private fun showInvalidFormatDialog()
    {
        val builder = AlertDialog.Builder( context )
        builder.setTitle( R.string.dialogTitle )
        builder.setMessage( R.string.dialogMessage )
        builder.setIcon( android.R.drawable.ic_dialog_alert )
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable( true )
        alertDialog.show()
    }

    private fun shouldPerformViewAction( result: Result ): String
    {
        return viewModel.canFileBeViewed( result )
    }

    override fun onActivityCreated(savedInstanceState: Bundle? )
    {
        super.onActivityCreated( savedInstanceState )
        viewModel = ViewModelProvider(this ).get( BookListViewModel::class.java )
        initializeRecyclerView()
        initActionBar()
        setHasOptionsMenu( true )
        initializeEditTextChangeListener()
        initializeOnTouchListener()
        arguments?.getString( GENRE_ARGS )?.let {
            GenreEnum.valueOf( it )
        }?.let { loadData( it ) }

    }

    private fun initializeRecyclerView()
    {
        mRecyclerView = recycler_view
        mRecyclerView.layoutManager = GridLayoutManager( activity, 3, LinearLayoutManager.VERTICAL, false)
        mAdapter = BookAdapter(mutableListOf()) {
            onItemClick(it)
        }
        mRecyclerView.adapter = mAdapter
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initializeOnTouchListener()
    {
        mRecyclerView.setOnTouchListener { v, event ->
            edit_text.clearFocus()
            val inputMethodManager =
                activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
            false
        }
    }

    private fun initializeEditTextChangeListener()
    {
        var lastChange: Long = 0
        edit_text.addTextChangedListener( object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int)
            {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
            {
            }

            override fun afterTextChanged(s: Editable?)
            {
                if( s.toString().length > 1 ) {
                    Handler().postDelayed( object : Runnable {
                        override fun run() {
                            if (System.currentTimeMillis() - lastChange >= 300) {
                                progressVisibility(View.VISIBLE)
                                viewModel.getBooksByQuery(s.toString())
                            }
                        }
                    }, 300 )
                }
            }
        })
    }

    private fun progressVisibility( visibility: Int )
    {
        if( visibility == View.GONE )
        {
            progressBar.visibility = visibility
            recycler_view.visibility = View.VISIBLE
        }
        else
        {
            progressBar.visibility = visibility
            recycler_view.visibility = View.GONE
        }
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

