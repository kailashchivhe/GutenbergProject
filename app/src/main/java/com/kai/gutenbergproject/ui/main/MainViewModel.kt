package com.kai.gutenbergproject.ui.main

import androidx.lifecycle.ViewModel
import com.kai.gutenbergproject.R
import com.kai.gutenbergproject.model.CategoryEnum
import com.kai.gutenbergproject.model.GenreItem

class MainViewModel : ViewModel()
{
    companion object
    {
        const val TAG = "MainViewModel"
    }

    fun getGenreList(): MutableList<GenreItem>
    {
        val genreList = mutableListOf<GenreItem>()

        val fictionItem = GenreItem( R.drawable.ic_fiction, CategoryEnum.FICTION.name )
        val dramaItem = GenreItem( R.drawable.ic_drama, CategoryEnum.DRAMA.name )
        val humorItem = GenreItem( R.drawable.ic_humour, CategoryEnum.HUMOR.name )
        val politicsItem = GenreItem( R.drawable.ic_politics, CategoryEnum.POLITICS.name )
        val philosophyItem = GenreItem( R.drawable.ic_philosophy, CategoryEnum.PHILOSOPHY.name )
        val historyItem = GenreItem( R.drawable.ic_history, CategoryEnum.HISTORY.name )
        val adventureItem = GenreItem( R.drawable.ic_adventure, CategoryEnum.ADVENTURE.name )

        genreList.add( fictionItem )
        genreList.add( dramaItem )
        genreList.add( humorItem )
        genreList.add( politicsItem )
        genreList.add( philosophyItem )
        genreList.add( historyItem )
        genreList.add( adventureItem )

        return genreList
    }
}