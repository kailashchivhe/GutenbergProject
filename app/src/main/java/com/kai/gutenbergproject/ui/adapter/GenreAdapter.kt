package com.kai.gutenbergproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kai.gutenbergproject.R
import com.kai.gutenbergproject.model.GenreItem
import kotlinx.android.synthetic.main.genre_item_layout.view.*

class GenreAdapter(private val mList: List<GenreItem>, private val listener: (GenreItem ) -> Unit) : RecyclerView.Adapter< GenreAdapter.MainViewHolder >()
{
    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): MainViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate( R.layout.genre_item_layout, parent, false )
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int)
    {
        holder.bind(mList[position], listener)
    }

    override fun getItemCount(): Int
    {
        return mList.size
    }

    class MainViewHolder( itemView: View ) : RecyclerView.ViewHolder( itemView )
    {
        fun bind(item: GenreItem, listener: (GenreItem) -> Unit) = with( itemView )
        {
            itemView.imageView.setImageResource( item.mIcon )
            itemView.textView.text = item.mTitle
            itemView.setOnClickListener{ listener( item ) }
        }
    }
}