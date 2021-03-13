package com.kai.gutenbergproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity()
{
    companion object
    {
        const val TAG = "MainActivity"
        const val GENRE_ARGS = "com.kai.gutenbergproject.genre_type"
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar( toolbar )
    }
}