package com.example.strangernews.ui.callback

interface ItemClickListener<T> {
    fun onItemClick(item: T?)
    fun onItemLongClick(item: T?): Boolean
}
