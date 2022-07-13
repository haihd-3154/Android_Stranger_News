package com.example.strangernews.ui.adapter

interface ItemClickListener<T> {
    fun onItemClick(item: T?)
    fun onItemLongClick(item: T?): Boolean
}
