package com.example.mvvmsecondmodel.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mvvmsecondmodel.R

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invis(){
    this.visibility = View.INVISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}

fun ImageView.loadImageFromUrl(url: String?){
    Glide.with(this.context)
        .load(url)
        .apply(RequestOptions.circleCropTransform().error(R.drawable.ic_baseline_person_24))
        .into(this)
}

fun ImageView.loadImageFromUrlRect(url: String){
    Glide.with(this.context)
        .load(url)
        .into(this)
}