package com.kai.gutenbergproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.kai.gutenbergproject.R
import com.kai.gutenbergproject.model.Author
import com.kai.gutenbergproject.model.Result
import kotlinx.android.synthetic.main.book_item_layout.view.*
import kotlinx.android.synthetic.main.genre_item_layout.view.imageView

class BookAdapter( private var mBookList: MutableList<Result>, private val listener: (Result) -> Unit ): RecyclerView.Adapter<BookAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val inflater = LayoutInflater.from( parent.context )
        val view = inflater.inflate( R.layout.book_item_layout, parent, false )
        return ViewHolder(view)
    }

    override fun onBindViewHolder( holder: ViewHolder, position: Int )
    {
        holder.bind( mBookList[position], listener )
    }

    fun setData( bookList: List<Result> )
    {
        mBookList.clear()
        mBookList.addAll( bookList )
    }

    override fun getItemCount(): Int
    {
        return mBookList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        fun bind( item: Result, listener: ( Result ) -> Unit) = with( itemView )
        {
            bookName.text = item.title
            bookAuthor.text = getAuthorNames( item.authors )
            Glide.
            with( imageView.context ).
            load( item.formats.jpegFormat ).
            diskCacheStrategy( DiskCacheStrategy.ALL ).
            placeholder( R.drawable.book ).
            into( imageView )
            itemView.setOnClickListener{ listener( item ) }
        }

        private fun getAuthorNames( authorList: List<Author> ): String
        {
            if( authorList.size == 1 )
            {
                return authorList[0].name;
            }
            else
            {
                var authorNames = ""
                authorList.forEachIndexed{index, item ->
                    println("index = $index, item = $item ")
                    authorNames = if( index == 0 ) {
                        item.name
                    } else {
                        authorNames + ", " + item.name
                    }
                }
                return authorNames
            }
        }
    }
}