package com.example.mvvmsecondmodel.ui

import com.example.mvvmsecondmodel.R
import com.example.mvvmsecondmodel.data.lokal.entity.Movie
import com.example.mvvmsecondmodel.util.loadImageFromUrl
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_movie_row.view.*

class MovieItem(val mymovie : Movie) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            itemView.txt_movie_title.text = mymovie.movie_title
            itemView.poster_img.loadImageFromUrl("http://image.tmdb.org/t/p/w185//" + mymovie.movie_poster)
        }
    }

    override fun getLayout(): Int = R.layout.item_movie_row
}