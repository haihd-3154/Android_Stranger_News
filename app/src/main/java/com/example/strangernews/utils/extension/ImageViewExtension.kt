package com.example.strangernews.utils.extension

import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.strangernews.R

fun ImageView.loadByDrawableRes(drawable: Int) {
    try {
        this.load(drawable) {
            crossfade(true)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun ImageView.loadByUrl(url: String?) {
    try {
        this.load(url) {
            crossfade(true)
            placeholder(R.drawable.img_default)
            error(R.drawable.img_default)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun ImageView.loadCircleImageByUrl(url: String?){
    try {
        this.load(url) {
            crossfade(true)
            error(R.drawable.img_default)
            transformations(CircleCropTransformation())
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
